/*
 * 文件名：ExcelAnalyseServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.CpuMemoryMidDao;
import com.bonc.nerv.tioa.week.entity.CpuMemoryMidEntity;
import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;
import com.bonc.nerv.tioa.week.service.ExcelAnalyseService;
import com.bonc.nerv.tioa.week.util.POIUtil;

/**
 * Excel分析服务类
 * @author yuanpeng
 * @version 2017年8月14日
 * @see ExcelAnalyseServiceImpl
 * @since
 */

@Service("excelAnalyseService")
public class ExcelAnalyseServiceImpl implements ExcelAnalyseService {
    
    /**
     * CpuMemoryMidDao
     */
    @Autowired
    private CpuMemoryMidDao cpuMemoryMidDao;

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
    
    /**
     * 解析yarn使用CPU内存资源统计信息表
     */
    @Override
    public List<CpuMemoryMidEntity> analyseYarnExcel(Workbook workbook) {
        
        //声明CpuMemoryMidEntity对象
        CpuMemoryMidEntity cpuMemoryMidEntity = null;
        
        //创建list对象，存储CpuMemoryMidEntity数据
        List<CpuMemoryMidEntity> cpuMemoryList = new ArrayList<CpuMemoryMidEntity>(); 
        
        
        if (workbook !=null) {
            
            //获取当前sheet工作表
            Sheet sheet = workbook.getSheetAt(0);
            
            //获得当前sheet的开始行  
            int firstRowNum = sheet.getFirstRowNum();
            
            //获得当前sheet的结束行  
            int lastRowNum = sheet.getPhysicalNumberOfRows();
            
            for (int rowNum = firstRowNum+2;rowNum <= lastRowNum; rowNum++) {
                
                //获取当前行数据
                Row row = sheet.getRow(rowNum);
                
                //遍历表中数据
                if( row!=null ){
                    cpuMemoryMidEntity = new CpuMemoryMidEntity();
                    cpuMemoryMidEntity.setSequenceName(POIUtil.getCellValue(row.getCell(1)));//获取队列名
                    cpuMemoryMidEntity.setCpuMax(POIUtil.getCellValue(row.getCell(2)));//获取CPU使用最大值
                    cpuMemoryMidEntity.setCpuAvg(POIUtil.getCellValue(row.getCell(3)));//CPU周平均值
                    cpuMemoryMidEntity.setMemoryMax(POIUtil.getCellValue(row.getCell(4)));//内存使用最大值(G)
                    cpuMemoryMidEntity.setMemoryAvg(POIUtil.getCellValue(row.getCell(5)));//内存周平均值(G)
                    cpuMemoryList.add(cpuMemoryMidEntity);
                }
            }
        }
        return cpuMemoryList;
    }
    
    /**
     * 将yarn使用CPU内存资源数据写入数据库
     */
    @Override
    public void yarnToDb(List<CpuMemoryMidEntity> reMidEntities) {
       
        if(reMidEntities !=null && reMidEntities.size()>0){
            cpuMemoryMidDao.save(reMidEntities);
            System.out.println("CPU内存资源数据写入数据库成功！");
        }
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
