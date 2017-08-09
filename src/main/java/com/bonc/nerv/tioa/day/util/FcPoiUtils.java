/*
 * 文件名：PoiUtils.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Administrator
 * 修改时间：2017年4月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bonc.nerv.tioa.day.util;


import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bonc.nerv.tioa.day.entity.FileCountEntity;


/**
 * 
 * 文件统计的专用导出类
 * @author yuanpeng
 * @version 2017年8月8日
 * @see FcPoiUtils
 * @since
 */
public class FcPoiUtils {
    /**
     * 
     * Description: <br>
     * 导出excel
     * @param sheetName sheet名
     * @param header  
     * @param context  
     * @return  HSSFWorkbook  
     * @see
     */
    public static XSSFWorkbook exportTest(String sheetName, String[] header,
                                          List<FileCountEntity> context) {
        XSSFWorkbook wb = new XSSFWorkbook(); // 声明一个工作薄
        XSSFSheet sheet = wb.createSheet(sheetName); //声明一个单子并命名
        sheet.setDefaultColumnWidth(5); //给单子名称一个长度
        XSSFCellStyle style = wb.createCellStyle(); // 生成一个样式  
        //设置这些样式  
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        XSSFFont font = wb.createFont(); //生成一个字体  
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font); //把字体应用到当前的样式  
        XSSFRow row = sheet.createRow(0); //创建第一行（也可以称为表头）
        row.setHeight((short)300);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //样式字体居中
        //给表头第一行一次创建单元格

        sheet.setDefaultRowHeight((short)1000);
        for (int i = 0; i < header.length; i++ ) {
            //            sheet.setColumnWidth(i, 10*512);
            XSSFCell cell = row.createCell(i);
            cell = row.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(style);

        }

        //body的样式
        XSSFCellStyle contentstyle = wb.createCellStyle(); //生成单元格的风格对象
        XSSFFont contentFont = wb.createFont(); //生成字体对象
        contentFont.setFontName("ARIAL"); //设置字体对象font的字体名为“ARIAL”
        contentFont.setBoldweight((short)(9)); //设置字体对象font的字体大小为12
        //contentFont.setBold(false);                             //设置字体对象font的字体加粗
        contentFont.setColor(HSSFColor.BLACK.index); //设置字体对象font的字体颜色为白色
        contentstyle.setFont(contentFont); //设置style的字体属性为font
        contentstyle.setBorderTop(XSSFCellStyle.BORDER_THIN); //设置上下左右的边框
        contentstyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        contentstyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        contentstyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        contentstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//headstyle的内容竖直方向居中对齐
        contentstyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        contentstyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        contentstyle.setFillForegroundColor(HSSFColor.WHITE.index);
        DataFormat format = wb.createDataFormat();
        contentstyle.setDataFormat(format.getFormat("@"));
        //大于10万一行的样式，需要红色字体
        XSSFCellStyle contentstyle10w = wb.createCellStyle();
        contentstyle10w.cloneStyleFrom(contentstyle);
        XSSFFont contentFont10w = wb.createFont(); //生成字体对象
        contentFont10w.setFontName("ARIAL"); //设置字体对象font的字体名为“ARIAL”
        contentFont10w.setColor(HSSFColor.RED.index);
        contentstyle10w.setFont(contentFont10w);
        
        //向单元格里填充数据
        for (short i = 0; i < context.size(); i++ ) {
            row = sheet.createRow(i + 1);
            XSSFCell conCell = row.createCell(0);
            sheet.setColumnWidth(0, 10 * 650);
            if (context.get(i).getTotalNum()> 100000) {
                conCell.setCellStyle(contentstyle10w);
            } else {
                conCell.setCellStyle(contentstyle);
            }
            conCell.setCellValue(context.get(i).getTenant());
            
            conCell = row.createCell(1);
            sheet.setColumnWidth(1, 10 * 250);
            if (context.get(i).getTotalNum()> 100000) {
                conCell.setCellStyle(contentstyle10w);
            } else {
                conCell.setCellStyle(contentstyle);
            }
            conCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            conCell.setCellValue(context.get(i).getFileNum());
            
            conCell = row.createCell(2);
            sheet.setColumnWidth(2, 10 * 250);
            if (context.get(i).getTotalNum()> 100000) {
                conCell.setCellStyle(contentstyle10w);
            } else {
                conCell.setCellStyle(contentstyle);
            }
            conCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            conCell.setCellValue(context.get(i).getFolderNum());
            
            conCell = row.createCell(3);
            sheet.setColumnWidth(3, 10 * 250);
            if (context.get(i).getTotalNum()> 100000) {
                conCell.setCellStyle(contentstyle10w);
            } else {
                conCell.setCellStyle(contentstyle);
            }
            conCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            conCell.setCellValue(context.get(i).getTotalNum());
          
        }
        
        CellRangeAddress c = CellRangeAddress.valueOf("A1:D1");
        sheet.setAutoFilter(c);
        
        return wb;
    }
    
    

}
