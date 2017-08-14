/*
 * 文件名：ExcelAnalyseService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;

/**
 * 
 * Excel分析服务类
 * @author yuanpeng
 * @version 2017年8月14日
 * @see ExcelAnalyseService
 * @since
 */
public interface ExcelAnalyseService {

    /**
     * 
     * Description: <br>
     * 分析Orcal的Excel表 
     * @return List<ResourceUsageMidEntity>
     * @see
     */
    List<ResourceUsageMidEntity> analyseOrcelExcel();
    
    /**
     * 
     * Description: <br>
     * 将oracl的使用量数据存入数据库
     * @param reMidEntities 
     * @see
     */
    void orcalToDb(List<ResourceUsageMidEntity> reMidEntities);
    /**
     * 
     * Description: <br>
     * 分析FTP的Excel表
     * 
     * @return List<ResourceUsageMidEntity>
     * @see
     */
    List<ResourceUsageMidEntity> analyseFtpExcel(Workbook workbook);
    
    /**
     * 
     * Description: <br>
     * 将ftp的使用量数据存入数据库
     * @param reMidEntities 
     * @see
     */
    void ftpToDb(List<ResourceUsageMidEntity> reMidEntities);
    /**
     * 
     * Description: <br>
     * 分析yarn的Excel表
     * @return List<ResourceUsageMidEntity>
     * @see
     */
    List<ResourceUsageMidEntity> analyseYarnExcel(Workbook workbook);
    
    /**
     * 
     * Description: <br>
     * 
     * 将yarn的使用量数据存入数据库
     * @param reMidEntities 
     * @see
     */
    void yarnToDb(List<ResourceUsageMidEntity> reMidEntities);
    /**
     * 
     * Description: <br>
     * 分析Hbase的Txt
     * @return List<ResourceUsageMidEntity>
     * @see
     */
    List<ResourceUsageMidEntity> analyseHbaseText(Workbook workbook);
    
    /**
     * 
     * Description: <br>
     * 将hbase的使用量数据存入数据库
     * @param reMidEntities 
     * @see
     */
    void hbaseToDb(List<ResourceUsageMidEntity> reMidEntities);
    /**
     * 
     * Description: <br>
     * 分析web服务器的Excel
     * @return List<ResourceUsageMidEntity>
     * @see
     */
    List<ResourceUsageMidEntity> analyseWebSeverExcel(Workbook workbook);
    
    /**
     * 
     * Description: <br>
     *  将webSever的使用量数据存入数据库
     *  @param reMidEntities 
     * @see
     */
    void webSeverToDb(List<ResourceUsageMidEntity> reMidEntities);
     
}
