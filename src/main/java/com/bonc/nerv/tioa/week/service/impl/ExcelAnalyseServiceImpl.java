/*
 * 文件名：ExcelAnalyseServiceImpl.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.service.impl;


import java.math.BigDecimal;
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
import com.bonc.nerv.tioa.week.util.POIUtil;


/**
 * Excel分析服务类
 * 
 * @author yuanpeng
 * @version 2017年8月14日
 * @see ExcelAnalyseServiceImpl
 * @since
 */
@Service("ExcelAnalyseService")
public class ExcelAnalyseServiceImpl implements ExcelAnalyseService {

    /**
     * ResourceUsageMidEntity的Dao
     */
    @Autowired
    private ResUsaMidDao resUsaMidDao;

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

        // 创建返回对象的数组
        List<ResourceUsageMidEntity> list = new ArrayList<>();

        if (workbook != null) {
            // 获得当前sheet工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            // 获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            // 循环除了第二行的所有行
            for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++ ) {
                // 获得当前行
                Row row = sheet.getRow(rowNum);
                if (row.getCell(0) == null || row.getCell(0).equals("")) {
                    break;
                }
                ResourceUsageMidEntity resourceUsageMidEntity = new ResourceUsageMidEntity();
                resourceUsageMidEntity.setType("web服务器");

                // 拿keyword Ip，并封装到实体
                Cell cell01 = row.getCell(0);
                resourceUsageMidEntity.setKeyword(POIUtil.getCellValue(cell01));
                // 拿租户空间已使用大小，并封装到实体
                Cell cell03 = row.getCell(3);
                String value = POIUtil.getCellValue(cell03);
                Double storageUsage = Double.parseDouble(value);
                // 保留两位小数，四舍五入
                BigDecimal bg = new BigDecimal(storageUsage);
                storageUsage = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                resourceUsageMidEntity.setStorageUsage(storageUsage);

                list.add(resourceUsageMidEntity);
            }
        }
        return list;

    }

    @Override
    public void webSeverToDb(List<ResourceUsageMidEntity> reMidEntities) {
        resUsaMidDao.save(reMidEntities);
    }

}
