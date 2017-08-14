/*
 * 文件名：ExcelAnalyseServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;
import com.bonc.nerv.tioa.week.service.ExcelAnalyseService;

/**
 * Excel分析服务类
 * @author yuanpeng
 * @version 2017年8月14日
 * @see ExcelAnalyseServiceImpl
 * @since
 */
@Service("ExcelAnalyseService")
public class ExcelAnalyseServiceImpl implements ExcelAnalyseService {

    /* (non-Javadoc)
     * @see com.bonc.nerv.tioa.week.service.ExcelAnalyseService#analyseOrcelExcel()
     * @author xiyan
     */
    @Override
    public List<ResourceUsageMidEntity> analyseOrcelExcel()   {
        
        List<ResourceUsageMidEntity> list = new ArrayList<ResourceUsageMidEntity>();
        InputStream instream;
        try {
            instream = new FileInputStream("F:/红楼人物.xlsx");
            Workbook  workbook = new XSSFWorkbook(instream);   
            //Sheet的下标是从0开始   
            //获取第一张Sheet表   
            Sheet readsheet = workbook.getSheetAt(0); 
            //获得当前sheet的开始行  
            int firstRowNum  = readsheet.getFirstRowNum();  
            //获得当前sheet的结束行  
            int lastRowNum = readsheet.getLastRowNum();  
            
            //循环第一行到倒数第二行
            for(int rowNum = firstRowNum+1;rowNum < lastRowNum;rowNum++){  
                //获得当前行  
                Row row = readsheet.getRow(rowNum);  
                if(row == null){  
                    continue;  
                }  
                ResourceUsageMidEntity resourceUsageMidEntity = new ResourceUsageMidEntity();
                Cell cell1 = row.getCell(0);  
                Cell cell2 = row.getCell(5);  
                resourceUsageMidEntity.setKeyword(cell1.toString());
                resourceUsageMidEntity.setStorageUsage(Double.parseDouble(cell2.toString()));
                resourceUsageMidEntity.setType("Orcal");
                list.add(resourceUsageMidEntity);  
            }  
            
        } catch (IOException e) {
            e.printStackTrace();
        }   
        

        
        
        return list;
    }


    @Override
    public void orcalToDb(List<ResourceUsageMidEntity> reMidEntities) {
        // TODO Auto-generated method stub
        
    }

    @SuppressWarnings("finally")
    @Override
    public  List<ResourceUsageMidEntity> analyseFtpExcel(Workbook workbook) {
        List<ResourceUsageMidEntity> list = new ArrayList<ResourceUsageMidEntity>();
        //Sheet的下标是从0开始   
        //获取第3张Sheet表   
        Sheet readsheet = workbook.getSheetAt(2); 
        //获得当前sheet的开始行  
        int firstRowNum  = readsheet.getFirstRowNum();  
        //获得当前sheet的结束行  
        int lastRowNum = readsheet.getLastRowNum();  
        //循环第一行到倒数第1行
        for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
            try {
                //获得当前行  
                Row row = readsheet.getRow(rowNum);  
                if(row == null){  
                    continue;  
                }  
                String cellstring = row.getCell(0).toString(); 
                if (cellstring.length()>=90) {//获取长度大于90的行   
                    StringBuffer stringBuffer = new StringBuffer(cellstring.substring(90));
                    while (stringBuffer.charAt(0)==' '){    //字符仍然为空
                        //减去为空的部分
                        stringBuffer = stringBuffer.deleteCharAt(0);
                    }
                    String string1 = null;   //数值
                    String string2 = null;   //路径
                    for(int i=0;i<stringBuffer.length();i++){
                        if (stringBuffer.charAt(i)=='/') {
                            string1 = stringBuffer.substring(0,i-1);//如果为0 只有1 位。
                            string2 = stringBuffer.substring(i,stringBuffer.length());
                            break;
                        }
                    }
                    ResourceUsageMidEntity resourceUsageMidEntity = new ResourceUsageMidEntity();
                    resourceUsageMidEntity.setKeyword(string2);
                    resourceUsageMidEntity.setType("FTP");
                    if (!string1.equals("0")) {   //String1 !=0
                        //对数值继续处理
                        String str1 = string1.substring(0,string1.length()-1);
                        char X = string1.charAt(string1.length()-1);
                        Double useing = Double.parseDouble(str1);
                        if (X == 'K') {
                            useing = useing/1024/1024;
                        }else if (X=='M') {
                            useing = useing/1024;
                        }else if (X=='t') {
                            useing = useing*1024;
                        }
                        resourceUsageMidEntity.setStorageUsage(useing);
                    }
                    else {//String1 ==0
                        resourceUsageMidEntity.setStorageUsage(0.0);
                    }
                    list.add(resourceUsageMidEntity);
                }
            } catch (Exception e) {
            e.printStackTrace();                
            }finally {
                continue;
            }
        }    
        return list;
    }

    

    @Override
    public void ftpToDb(List<ResourceUsageMidEntity> reMidEntities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ResourceUsageMidEntity> analyseYarnExcel(Workbook workbook) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void yarnToDb(List<ResourceUsageMidEntity> reMidEntities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ResourceUsageMidEntity> analyseHbaseText(Workbook workbook) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void hbaseToDb(List<ResourceUsageMidEntity> reMidEntities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ResourceUsageMidEntity> analyseWebSeverExcel(Workbook workbook) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void webSeverToDb(List<ResourceUsageMidEntity> reMidEntities) {
        // TODO Auto-generated method stub
        
    }
    
}
