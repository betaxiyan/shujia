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

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.ResourceUsageMidDao;
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
    private ResourceUsageMidDao resourceUsageMidDao;
    
    @Override
    public List<ResourceUsageMidEntity> analyseOrcelExcel() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void orcalToDb(List<ResourceUsageMidEntity> reMidEntities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ResourceUsageMidEntity> analyseFtpExcel(Workbook workbook) {
        // TODO Auto-generated method stub
        return null;
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
        resourceUsageMidDao.save(reMidEntities);
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
     * 读取指定路径的txt文件
     * @param filePath 
     * @see
     */
     private void readTxt(String filePath) {
        
        
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
