/*
 * 文件名：ExcelAnalyseServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.ResUsaMidDao;
import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;
import com.bonc.nerv.tioa.week.service.ExcelAnalyseService;

/**
 * Excel分析服务类
 * @author yuanpeng
 * @version 2017年8月14日
 * @see ExcelAnalyseServiceImpl
 * @since
 */

@Service
public class ExcelAnalyseServiceImpl implements ExcelAnalyseService {

    @Autowired
    
    private ResUsaMidDao resUsaMidDao;

    /* (non-Javadoc)
     * @see com.bonc.nerv.tioa.week.service.ExcelAnalyseService#analyseOrcelExcel()
     * @author xiyan
     */
    @SuppressWarnings("finally")
    @Override
    public List<ResourceUsageMidEntity> analyseOrcelExcel(Workbook workbook)   {
        List<ResourceUsageMidEntity> list = new ArrayList<ResourceUsageMidEntity>();
        //获取第一张Sheet表   
        Sheet readsheet = workbook.getSheetAt(0); 
        //获得当前sheet的开始行  
        int firstRowNum  = readsheet.getFirstRowNum();  
        //获得当前sheet的结束行  
        int lastRowNum = readsheet.getLastRowNum();  
            
        //循环除了第一行的所有行,此处不加一即可不跳过第一行
        //修改之处。
        for(int rowNum = firstRowNum+1;rowNum < lastRowNum;rowNum++){  
            try{
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
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                continue;
            }
        }   
        return list;
    }


    @Override
    public void orcalToDb(List<ResourceUsageMidEntity> reMidEntities) {
        
        resUsaMidDao.save(reMidEntities);

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
        
        resUsaMidDao.save(reMidEntities);

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

    
    /**
     * 分析Hbase的Txt
     */
    @Override
    public List<ResourceUsageMidEntity> analyseHbaseText(String filePath) {
        List<ResourceUsageMidEntity> list = new ArrayList<ResourceUsageMidEntity>();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    String [] arr = lineTxt.split("\\s+");
                    if (arr.length < 8) {
                        continue;
                    }
                    ResourceUsageMidEntity resourceUsageMidEntity = new ResourceUsageMidEntity();
                    resourceUsageMidEntity.setType("hbase");
                    resourceUsageMidEntity.setKeyword(arr[8]);
                    resourceUsageMidEntity.setStorageUsage(getDouble(arr[7]));
                    list.add(resourceUsageMidEntity);
                }
                br.close();
            }
            else {
                System.out.println("文件不存在!");
            }
        }
        catch (Exception e) {
            System.out.println("文件读取错误!");
        }
       
        return list;
    }

    @Override
    public void hbaseToDb(List<ResourceUsageMidEntity> reMidEntities) {
        resUsaMidDao.save(reMidEntities);
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
    
    
     /**
      * 将字符型转换成double型数据
      * @param str  
      * @return data
      * @see
      */
    private Double getDouble(String str) {
        double data;
        data = Double.parseDouble(str);
        data = data / (1024 * 1024);
        DecimalFormat df=new DecimalFormat("######.##");
        String st=df.format(data);
        return Double.parseDouble(st);
    }
}
