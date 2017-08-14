/*
 * 文件名：ExcelAnalyseServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;
import com.bonc.nerv.tioa.week.service.ExcelAnalyseService;

/**
 * Excel分析服务类
 * @author yuanpeng
 * @version 2017年8月14日
 * @see ExcelAnalyseServiceImpl
 * @since
 */

public class ExcelAnalyseServiceImpl implements ExcelAnalyseService {

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
