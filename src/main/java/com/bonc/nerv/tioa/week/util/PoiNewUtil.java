/*
 * 文件名：PoiNewUtil.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月9日
 */

package com.bonc.nerv.tioa.week.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bonc.nerv.tioa.week.dao.TenretiredDao;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
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
    public static XSSFWorkbook createWorkBook(String sheetName, String[] headers ,List<List<TenretiredEntity>> ttEntityLists,
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
        createSheet(sheet, headers, ttEntityLists, mergeClom);
        
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
    public static XSSFSheet createSheet(XSSFSheet sheet, String[] headers ,List<List<TenretiredEntity>> ttEntityLists,
                                        Integer[] mergeClom ){
        XSSFRow row = sheet.createRow(0); // 产生表格标题行  
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            //cell.setCellStyle(headStyle);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        int startRow ;
        //大行
        for(int i=0; i<ttEntityLists.size() ;i++){
            int listSize = ttEntityLists.get(i).size();
            //生成待合并行
            int rowNum=sheet.getLastRowNum()+1;//获得总行数
            startRow = rowNum; //起始行的行号
            for(int n =0; n< listSize; n++){
                row = sheet.createRow(rowNum);
                rowNum=sheet.getLastRowNum()+1;//更新总行数
                //生成列
                XSSFCell cell = row.createCell(0);
                cell.setCellValue(ttEntityLists.get(i).get(n).getTenantName());
                cell = row.createCell(1);
                cell.setCellValue(ttEntityLists.get(i).get(n).getTenantLevel());
            }
            //new CellRangeAddress(0-base, 0-base, 0-based, 0-based)
            for(int j =0 ;j<mergeClom.length; j++){
                sheet.addMergedRegion(new CellRangeAddress(startRow, rowNum-1, mergeClom[j], mergeClom[j]));
            }
            
        }
        
        return null;
        
    }
}
