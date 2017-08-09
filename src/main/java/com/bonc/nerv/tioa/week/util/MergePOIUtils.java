/*
 * 文件名：MergePOIUtils.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月7日
 */

package com.bonc.nerv.tioa.week.util;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导出多张表到同一excel文件工具类
 * @author zhiyong
 * @version 2017年8月7日
 * @see MergePOIUtils
 * @since
 */
public class MergePOIUtils {
    /**
     * @param fileName 文件名称
     * @param headers 表头
     * @param dataset 数据集
     * @param isSortDataSet 是否对数据排序
     * @param mergeBasis 合并基准列 可选
     * @param mergeCells 要合并的列 可选
     * @param sumCellsMap 要求和的列 可选
     * @param timeCells 时间列 可选
     * @throws IOException IOE异常
     */
    public static void exportExelMerge(String fileName,final String[] headers,List<String[]> dataset,boolean isSortDataSet,HttpServletResponse response, final Integer[] mergeBasis, final Integer[] mergeCells, final Integer[] sumCells, final Integer[] timeCells)
        throws IOException{
        String title = "Sheet1";
        response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        
        createExcelMerge(title,headers,dataset,isSortDataSet,mergeBasis,mergeCells,sumCells,timeCells);
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
    }
    
    /**
     * @param title 文件名称
     * @param headers 表头
     * @param dataset 数据集
     * @param isSortDataSet 是否对数据排序
     * @param mergeBasis 合并基准列 可选
     * @param mergeCells 要合并的列
     * @param sumCells 要求和的列
     * @param timeCells 时间列 可选
     */
    public static XSSFWorkbook createExcelMerge(String title, final String[] headers,List<String[]> dataset,boolean isSortDataSet,
                                                final Integer[] mergeBasis, final Integer[] mergeCells, final Integer[] sumCells, final Integer[] timeCells){
        XSSFWorkbook workbook = new XSSFWorkbook();
        if(headers == null || headers.length <= 0){
            return workbook;
        }
        XSSFSheet sheet = workbook.createSheet(title);
        createSheetMerge(title, headers,dataset,isSortDataSet,
            mergeBasis, mergeCells, 
            sumCells, timeCells, sheet, workbook);
        return workbook;
    }
    
    public static void createSheetMerge(String title, final String[] headers,List<String[]> dataset,boolean isSortDataSet,
                                                final Integer[] mergeBasis, final Integer[] mergeCells, 
                                                final Integer[] sumCells, final Integer[] timeCells, XSSFSheet sheet, XSSFWorkbook workbook){
        sheet.setDefaultColumnWidth(15); // 设置表格默认列宽度为15个字节  
        XSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式 
        XSSFCellStyle commonDataStyle = createCommonDataStyle(workbook); // 生成一般数据样式  
        XSSFRow row = sheet.createRow(0); // 产生表格标题行  
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        
        if(isSortDataSet && mergeBasis != null && mergeBasis.length > 0){ //是否排序数据
            Collections.sort(dataset, new Comparator<String[]>() {
                public int compare(String[] o1, String[] o2) {
                    String s1 = "";
                    String s2 = "";
                    for(int i = 0 ; i < mergeBasis.length ; i++){
                        s1+=(o1[mergeBasis[i].intValue()]+Character.valueOf((char)127).toString());
                        s2+=(o2[mergeBasis[i].intValue()]+Character.valueOf((char)127).toString());
                    }
                    if(timeCells != null && timeCells.length > 0){
                        for(int i = 0 ; i < timeCells.length ; i++){
                            s1+= o1[timeCells[i].intValue()];
                            s2+= o2[timeCells[i].intValue()];
                        }
                    }
                    if(s1.compareTo(s2) < 0){
                        return -1;
                    }else if(s1.compareTo(s2) == 0){
                        return 0;
                    }else{
                        return 1;
                    }
                }
            });
        }
        // 遍历集合数据，产生数据行  
        Iterator<String[]> it = dataset.iterator();  
        int index = 0;  
        while (it.hasNext()) {
            index++;  
            row = sheet.createRow(index);  
            String[] dataSources = it.next() ;
            for (int i = 0; i < dataSources.length; i++) {  
                XSSFCell cell = row.createCell(i);  
                cell.setCellStyle(commonDataStyle);
                cell.setCellValue(dataSources[i]);
            }
        }  
        try {  
            if(mergeBasis != null && mergeBasis.length > 0 && mergeCells != null && mergeCells.length > 0){
                for(int i = 0 ; i < mergeCells.length ; i++){
                    mergedRegion(sheet,mergeCells[i],1,sheet.getLastRowNum(),workbook,mergeBasis);
                }
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
    
    
    }

    
    /**
     * 合并单元格
     * @param sheet
     * @param cellLine
     * @param startRow
     * @param endRow
     * @param workbook
     * @param mergeBasis
     */
    private static void mergedRegion(XSSFSheet sheet, int cellLine,int startRow, int endRow, XSSFWorkbook workbook, Integer[] mergeBasis) {
        XSSFCellStyle style = workbook.createCellStyle();           // 样式对象  
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  // 垂直  
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);             // 水平  
        String s_will = sheet.getRow(startRow).getCell(cellLine).getStringCellValue();  // 获取第一行的数据,以便后面进行比较  
        int count = 0;  
        Set<Integer> set = new HashSet<Integer>();
        CollectionUtils.addAll(set, mergeBasis);
        for (int i = 2; i <= endRow; i++) {  
            String s_current = sheet.getRow(i).getCell(cellLine).getStringCellValue();  
            if (s_will.equals(s_current)) { 
                boolean isMerge = true;
                if(!set.contains(cellLine)){//如果不是作为基准列的列 需要所有基准列都相同
                    for(int j = 0 ; j < mergeBasis.length ; j++){
                        if(!sheet.getRow(i).getCell(mergeBasis[j]).getStringCellValue()
                                .equals(sheet.getRow(i-1).getCell(mergeBasis[j]).getStringCellValue())){
                            isMerge = false;
                        }
                    }
                }else{//如果作为基准列的列 只需要比较列号比本列号小的列相同
                    for(int j = 0 ; j < mergeBasis.length && mergeBasis[j] < cellLine ; j++){
                        if(!sheet.getRow(i).getCell(mergeBasis[j]).getStringCellValue()
                                .equals(sheet.getRow(i-1).getCell(mergeBasis[j]).getStringCellValue())){
                            isMerge = false;
                        }
                    }
                }
                if(isMerge){
                    count++;
                }else{
                    sheet.addMergedRegion(new CellRangeAddress( startRow, startRow+count,cellLine , cellLine));
                    startRow = i;  
                    s_will = s_current;
                    count = 0;
                }
            } else {  
                sheet.addMergedRegion(new CellRangeAddress( startRow, startRow+count,cellLine , cellLine));  
                startRow = i;  
                s_will = s_current;
                count = 0;
            }  
            if (i == endRow && count > 0) {  
                sheet.addMergedRegion(new CellRangeAddress(startRow,startRow+count ,cellLine , cellLine));  
            }  
        }  
    }

    /**
     * 标题单元格样式
     * @param workbook
     * @return
     */
    private static XSSFCellStyle createHeadStyle(XSSFWorkbook workbook){
        //标题单元格样式
        XSSFCellStyle headStyle = workbook.createCellStyle();   
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());  
        headStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  
        headStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
        headStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
        headStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
        headStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
        headStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
        //标题单元格字体  
        XSSFFont headFont = workbook.createFont();  
        headFont.setColor(IndexedColors.BLACK.getIndex());  
        headFont.setFontHeightInPoints((short) 12);  
        headFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        headStyle.setFont(headFont);  
        return headStyle;
    }
    
    /**
     * 普通数据单元格样式 
     * @param workbook
     * @return
     */
    private static XSSFCellStyle createCommonDataStyle(XSSFWorkbook workbook){
        //普通数据单元格样式 
        XSSFCellStyle commonDataStyle = workbook.createCellStyle();  
        commonDataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());  
        commonDataStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  
        commonDataStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
        commonDataStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
        commonDataStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
        commonDataStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
        commonDataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
        commonDataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
        //普通数据单元格字体  
        XSSFFont commonDataFont = workbook.createFont();  
        commonDataFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);  
        //把字体应用到当前的样式  
        commonDataStyle.setFont(commonDataFont); 
        return commonDataStyle;
    }
}
