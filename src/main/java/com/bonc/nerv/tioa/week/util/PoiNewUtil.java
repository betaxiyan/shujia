/*
 * 文件名：PoiNewUtil.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月9日
 */

package com.bonc.nerv.tioa.week.util;

import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yuanpeng
 * @version 2017年8月9日
 * @see PoiNewUtil
 * @since
 */
public class PoiNewUtil {
    
    /**
     * 
     * Description: <br>
     * @param sheetName sheet名字
     * @param headers 表头
     * @param ttEntityLists 数据集
     * @param mergeClom 合并列列号
     * @return XSSFWorkbook
     * @see
     */
    public static XSSFWorkbook createWorkBook(String sheetName, String[] headers ,List<List<String[]>> ttEntityLists,
                                              Integer[] mergeClom){
        XSSFWorkbook workbook = new XSSFWorkbook();
        //判断表头是否为空
        if(headers == null || headers.length <= 0){
            return workbook;
        }
        if(sheetName == null || sheetName.length() <= 0){
            return workbook;
        }
        XSSFSheet sheet = workbook.createSheet(sheetName);
        createSheet(workbook, sheet, headers, ttEntityLists, mergeClom);
        return workbook;
        
    }
    
    
    /**
     * 
     * Description: <br>
     * 组装sheet
     * @param sheet 一个传入的sheet
     * @param headers 表头
     * @param ttEntityLists 数据集
     * @param mergeClom 合并列列号
     * @return XSSFSheet
     * @see
     */
    public static XSSFSheet createSheet(XSSFWorkbook workbook, XSSFSheet sheet, String[] headers ,List<List<String[]>> ttEntityLists,
                                        Integer[] mergeClom ){
        XSSFRow row = sheet.createRow(0); // 产生表格标题行  
        XSSFCellStyle headStyle = createHeadStyle(workbook);
        XSSFCellStyle bodyStyle = createBodyStyle(workbook);
        fixheader(row, headStyle, headers);  //组装表头
        //int startRow ;
        //大行
        for(int i=0; i<ttEntityLists.size() ;i++){
            int listSize = ttEntityLists.get(i).size();
            //生成待合并行
            int rowNum=sheet.getLastRowNum()+1;//获得总行数
            int startRow = rowNum; //起始行的行号
            for(int n =0; n< listSize; n++){
                row = sheet.createRow(rowNum);
                rowNum=sheet.getLastRowNum()+1;//更新总行数
                //生成列
                for(int c =0; c< ttEntityLists.get(i).get(n).length; c++){
                    XSSFCell cell = row.createCell(c);
                    cell.setCellValue(ttEntityLists.get(i).get(n)[c]);
                    cell.setCellStyle(bodyStyle);
                }
            }
            //new CellRangeAddress(0-base, 0-base, 0-based, 0-based)
            for(int j =0 ;j<mergeClom.length; j++){
                sheet.addMergedRegion(new CellRangeAddress(startRow, rowNum-1, mergeClom[j], mergeClom[j]));
            }
            
        }
        
        return null;
        
    }
    /**
     * 
     * Description: <br>
     * 组装表头
     * 
     * @param row XSSFRow
     * @param headers 表头
     * @see
     */
    public static void fixheader(XSSFRow row, XSSFCellStyle headStyle , String[] headers){
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            //cell.setCellStyle(headStyle);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
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
        headFont.setFontName("微软雅黑");
        // 把字体应用到当前的样式  
        headStyle.setFont(headFont);  
        return headStyle;
    }
    
    private static XSSFCellStyle createBodyStyle(XSSFWorkbook workbook){
        XSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        bodyStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框   
        bodyStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框   
        bodyStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框   
        bodyStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框  
      //标题单元格字体  
        XSSFFont bodyFont = workbook.createFont();  
        bodyFont.setColor(IndexedColors.BLACK.getIndex());  
        bodyFont.setFontName("微软雅黑");
        bodyStyle.setFont(bodyFont);
        return bodyStyle;
    }
    
}
