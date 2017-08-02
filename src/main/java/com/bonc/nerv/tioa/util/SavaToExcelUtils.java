/*
 * 文件名：SavaToExcelUtils.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：xiyan
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.util;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 将tioa数据保存为特定格式的Execl文件
 * 
 * @author xiyan
 * @version 2017年8月1日
 * @see SavaToExcelUtils
 * @since
 */

public class SavaToExcelUtils {
    /**
     *  默认宽度
     */
    private static final int DEFAULT_COLUMN_SIZE = 30;
    
    /**
     * 断言Excel文件写入之前的条件
     *
     * @param directory 目录
     * @param fileName  文件名
     * @return file
     * @throws IOException IO异常
     */
    private static File assertFile(String directory, String fileName) throws IOException {
        File tmpFile = new File(directory + File.separator + fileName + ".xlsx");
        if (tmpFile.exists()) {
            if (tmpFile.isDirectory()) {
                throw new IOException("File '" + tmpFile + "' exists but is a directory");
            }
            if (!tmpFile.canWrite()) {
                throw new IOException("File '" + tmpFile + "' cannot be written to");
            }
        } else {
            File parent = tmpFile.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent + "' could not be created");
                }
            }
        }
        return tmpFile;
    }
    
    /**
     * 日期转化为字符串,格式为yyyyMMdd
     * @param date  日期
     * @return String   String类型的日期
     */
    private static String getCnDate(Date date) {
        String format = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    
    /**
     * Excel 导出，POI实现 
     * @param directory   文件路径
     * @param fileName    文件名
     * @param sheetName   sheet页名称
     * @param columnNames 表头列表名
     * @param sheetTitle  sheet页Title
     * @param objects     目标数据集
     * @return File       写入的文件
     * @throws ParseException   Parse异常
     * @throws IOException  IO异常
     */
    public static File writeExcel(String directory, String fileName, String sheetName, List<String> columnNames,
                                  List<String>  sheetTitle, List<List<Object>> objects) throws  IOException, ParseException {
        File tmpFile = assertFile(directory, fileName);
        return exportExcel(tmpFile, sheetName, columnNames, sheetTitle, objects);
    }
    
    /**
     * 导出字符串数据
     * @param file        文件名
     * @param sheetName sheet名字 
     * @param columnNames 表头
     * @param sheetTitle  sheet页Title
     * @param objects     目标数据
     * @return File       返回文件
     * @throws ParseException   Parse异常
     * @throws IOException  Report异常
     */
    @SuppressWarnings("finally")
    private static File exportExcel(File file, String sheetName, List<String> columnNames,
                                    List<String> sheetTitle, List<List<Object>> objects) throws  IOException, ParseException {
        // 声明一个工作薄
        Workbook workBook;
        // 声明一个工作薄
        workBook = new XSSFWorkbook();
        
        Map<String, CellStyle> cellStyleMap = styleMap(workBook);
        // 表头样式
        CellStyle headStyle = cellStyleMap.get("head");
        // 正文样式
        CellStyle contentStyle = cellStyleMap.get("content");
        //黄色背景
        CellStyle yellowStyle = cellStyleMap.get("yellow");
        // 生成一个表格
        Sheet sheet = workBook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workBook.createSheet(sheetName);
        }
        //最新Excel列索引,从0开始
        int lastRowIndex = sheet.getLastRowNum();
        if (lastRowIndex > 0) {
            lastRowIndex++;
        }
        // 设置表格默认列宽度
        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_SIZE);
        // 合并单元格
        //设置“租户”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 0, 0));
        //设置“服务类型”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 1, 1));
        //设置“租户分类”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 2, 2));
        //设置“资源具备日期”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex, 3, 5));
        //设置“计费日期确定”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex, 6, 12));
        //设置“联通引入方”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 13, 13));
        //设置“引入方联系人”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 14, 14));
        //设置“联系方式”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 15, 15));
        //设置“申请日期”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 16, 16));
        //设置“是否签署合同”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 17, 17));
        //设置“月租备注”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 18, 18));
        //设置“退租时间”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 19, 19));
        //设置“备注”，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex+1, 20, 20));
        
        
        // 产生表格标题行
        Row rowMerged = sheet.createRow(lastRowIndex);
        lastRowIndex++;
        for (int i = 0; i < sheetTitle.size(); i++) {
            Cell cell = rowMerged.createCell(i);
            cell.setCellStyle(headStyle);
            RichTextString text = new XSSFRichTextString(sheetTitle.get(i));
            cell.setCellValue(text);
        }
        
        
        // 产生表格表头列标题行
        Row row = sheet.createRow(lastRowIndex);
        lastRowIndex++;
        for (int i = 0; i < columnNames.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            RichTextString text = new XSSFRichTextString(columnNames.get(i));
            cell.setCellValue(text);
        }
        // 遍历集合数据,产生数据行,前两行为标题行与表头行
        for (List<Object> dataRow : objects) {
            try{
                row = sheet.createRow(lastRowIndex);
                lastRowIndex++;
            
                String type = (String)dataRow.get(2);
                if (!type.equals("10")) {
                    //该行隐藏
                    row.setZeroHeight(true);
                }
                Boolean flag = false;//是否为黄色标记
                String format = "yyyyMMdd";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                String enddata = (String) dataRow.get(12);//拿到第十三行的到期日期 
                if (enddata.length()>=8) {
                    enddata=enddata.substring(0,8);
                    Date d1 = sdf.parse(enddata);
                    if (daysBetween(new Date(),d1)<3 && daysBetween(new Date(),d1)>=0) {//如果日期小于三天
                        //该行黄色
                        flag = true;
                    }
                }
            
                for (int j = 0; j < dataRow.size(); j++) {
                    Cell contentCell = row.createCell(j);
                    Object dataObject = dataRow.get(j);
                    if (flag) {
                        contentCell.setCellStyle(yellowStyle);//黄色
                    }else {
                        contentCell.setCellStyle(contentStyle);//常规类型
                    }
                
                    if (dataObject != null) {//不为空
                        if (dataObject instanceof Date) {//日期类型
                            contentCell.setCellValue(getCnDate((Date)dataObject));
                        }else {//非日期类型
                            contentCell.setCellValue(dataObject.toString());
                        }
                    }else {//如果数据为空, 设置单元格内容为字符型
                        contentCell.setCellValue("");
                    }   
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                continue;
            }
                
        }
        //调整列宽
        for(int col =0;col<21;col++){
            sheet.autoSizeColumn((short)col); //调整第col列宽度
        }
        
        
        try {
            OutputStream ops = new FileOutputStream(file);
            workBook.write(ops);
            ops.flush();
            ops.close();
        } catch (IOException e) {
            throw new IOException(e);
        }
        return file;
    }

  
    
    /**
     * 创建单元格标题行样式
     * @param workbook  工作簿
     * @return CellStyle 样式
     * @see 
     */
    @SuppressWarnings("deprecation")
    private static CellStyle createCellHeadStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //设置对齐样式
        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
        // 生成字体
        Font font = workbook.createFont();
        // 表头样式
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);//设置前景填充样式
        style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);//前景填充色
        
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 创建单元格正文样式
     *
     * @param workbook 工作薄
     * @return CellStyle 样式
     */
    @SuppressWarnings("deprecation")
    private static CellStyle createCellContentStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //设置对齐样式
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 生成字体
        Font font = workbook.createFont();
        // 正文样式
        style.setFillPattern(XSSFCellStyle.NO_FILL);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 创建单元格黄色背景样式
     *
     * @param workbook 工作薄
     * @return CellStyle 样式
     */
    @SuppressWarnings("deprecation")
    private static CellStyle createCellYellowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //设置对齐样式
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 生成字体
        Font font = workbook.createFont();
        // 正文样式
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);//设置前景填充样式
        style.setFillForegroundColor(HSSFColor.YELLOW.index);//前景填充色
        
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    

  
    /**
     * 单元格样式列表
     * @param workbook 工作簿
     * @return Map CellStyle的Map
     */
    private static Map<String, CellStyle> styleMap(Workbook workbook) {
        Map<String, CellStyle> styleMap = new LinkedHashMap<>();
        styleMap.put("head", createCellHeadStyle(workbook));
        styleMap.put("content", createCellContentStyle(workbook));
        styleMap.put("yellow", createCellYellowStyle(workbook));
        return styleMap;
    }


    /**
     * Description:两个日期之间的天数 
     * @param smdate 第一个日期
     * @param bdate 第二个日期
     * @return int 日期之间的天数 
     * @throws ParseException 
     * @see 
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long betweendays=(time2-time1)/(1000*3600*24);  
            
        return Integer.parseInt(String.valueOf(betweendays));           
    }    
    
}
