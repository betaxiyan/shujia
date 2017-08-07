/*
 * 文件名：POIUtil.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.week.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;


/**
 * 
 * 读取Excel
 * @author leijin
 * @version 2017年8月1日
 * @see POIUtil
 * @since
 */
public class POIUtil {
    /**
     * xls文档
     */
    private static final String XLS = "xls";

    /**
     * xlsx文档
     */
    private static final String XLSX = "xlsx";


    
    /**
     * 从数据库导出到Excel
     */
    
    public static void exportExcel(List<TioaTenantAroundShowEntity> list){
        
        
        HSSFWorkbook wb = new HSSFWorkbook(); //创建工作簿
        //创建工作表
        HSSFSheet sheet = wb.createSheet("租户周边信息管理表");
        for (int i = 0;i < 3; i ++) {
            //设置列宽
            sheet.setColumnWidth(i, 3000);
        }
        //创建行
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(30);//设置行高
        //创建单元格
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("租户周边信息管理表");
        
        //标题样式
        //创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置单元格居中对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置单元格内容显示不下自动换行
        cellStyle.setWrapText(true);
        //设置单元格字体样式
        HSSFFont font = wb.createFont();
        //设置字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        //设置单元格边框为细线条  
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
        //设置单元格样式
        cell.setCellStyle(cellStyle);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));//合并0行2列
        
        HSSFRow row1 = sheet.createRow(1);
        //标题信息
        String[] titles = {"表Id","租户Id","租户名","租户级别","租户负责人","租户负责人电话","统一平台个数","4A个数","需求","平台接口人"};
        for (int i = 0;i < titles.length; i ++) {
            HSSFCell cell1 = row1.createCell(i);
            cell1.setCellValue(titles[i]);
            //设置单元格样式
            cell1.setCellStyle(cellStyle);
        }
        
       //内容样式
        //创建单元格样式
        HSSFCellStyle cellStyle2 = wb.createCellStyle();  
        // 设置单元格居中对齐  
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 设置单元格垂直居中对齐  
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 创建单元格内容显示不下时自动换行  
        cellStyle2.setWrapText(true);  
        // 设置单元格字体样式  
        HSSFFont font2 = wb.createFont();  
        // 设置字体加粗  
        //font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font2.setFontName("宋体");  
        font2.setFontHeight((short) 200);  
        cellStyle2.setFont(font2);  
        // 设置单元格边框为细线条  
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //循环赋值  
        for(int i=0;i<list.size();i++){  
            HSSFRow row2 = sheet.createRow(i+2);  
            row2.createCell(0).setCellValue(list.get(i).getTtaId());
            row2.createCell(1).setCellValue(list.get(i).getTenantId());
            row2.createCell(2).setCellValue(list.get(i).getTenantName());
            row2.createCell(3).setCellValue(getStr(list.get(i).getTenantLevel()));
            row2.createCell(4).setCellValue(list.get(i).getTenantBoss());
            row2.createCell(5).setCellValue(list.get(i).getTenantTel());
            row2.createCell(6).setCellValue(getStr(list.get(i).getNumOfUnifiedPlatform()));
            row2.createCell(7).setCellValue(getStr(list.get(i).getNumOf4a()));
            row2.createCell(8).setCellValue(list.get(i).getTenantReqirement());
            row2.createCell(9).setCellValue(list.get(i).getTenantInterface());       
          //设置单元格样式  
            for (int j = 0;j < 10; j ++) {
                row2.getCell(j).setCellStyle(cellStyle2);
            }
            
            
        }  
        File file = new File("D://excel.xls");  
        if(!file.exists()){  
            try {
                file.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file);  
                wb.write(fileOut);  
                fileOut.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        }  
    }
    
    
    /**
     * 读取Excel文档
     * @param file  
     * @return list
     * @throws IOException 
     * @see
     */
    public static List<String[]> readExcel(MultipartFile file)
        throws IOException {

        //检查文件是否存在
        checkFile(file);

        //获得工作簿对象
        Workbook workbook = getWorkBook(file);

        //创建返回对象，把每行的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();

        if (workbook != null) {
            // for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){  
            //获得当前sheet工作表  
            Sheet sheet = workbook.getSheetAt(2);
            /*if(sheet == null){  
                continue;  
            }*/
            //获得当前sheet的开始行  
            int firstRowNum = sheet.getFirstRowNum();

            //获得当前sheet的结束行  
            int lastRowNum = sheet.getLastRowNum();
            //循环除了第二行的所有行  
            for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++ ) {
                //获得当前行  
                Row row = sheet.getRow(rowNum);
                if (row.getCell(0) == null || row.getCell(0).equals("")) {
                    break;
                }
                //获得当前行的开始列  
                int firstCellNum = row.getFirstCellNum();
                //获得当前行的列数  
                int lastCellNum = row.getPhysicalNumberOfCells();
                String[] cells = new String[row.getPhysicalNumberOfCells()];
                //循环当前行  
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++ ) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                list.add(cells);
            }
            //}  
            //workbook.close();
        }
        return list;
    }

    /**
     * 判断文件是否存在
     * @param file  
     * @throws IOException 
     * @see
     */
    public static void checkFile(MultipartFile file)
        throws IOException {
        //判断文件是否存在  
        if (null == file) {
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件  
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            throw new IOException(fileName + "不是excel文件");
        }
    }

    /**
     * 获得Workbook对象
     * @param file  

     * @return workbook
     * @see
     */
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;
        try {
            //获取excel文件的io流  
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if (fileName.endsWith(XLS)) {
                //2003  
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(XLSX)) {
                //2007  
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {}
        return workbook;
    }

    /**
     * 
     * @param cell 
     * @return String
     * @see
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况  
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型  
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字  
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
   
    private static String getStr(Integer data) {
        if (null == data) {
            return "";
        }
        return data.toString();
    }
    
}
