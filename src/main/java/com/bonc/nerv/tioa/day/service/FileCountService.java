/*
 * 文件名：FileCountService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.day.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bonc.nerv.tioa.day.entity.FileCountEntity;

/**
 * 
 * 文件统计服务类
 * @author yuanpeng
 * @version 2017年8月6日
 * @see FileCountService
 * @since
 */
public interface FileCountService {

    /**
     * 
     * Description: <br>
     * 查找一条记录的service方法
     * @param fcId 
     * @return FileCountEntity
     * @see
     */
    FileCountEntity findOneFc(Long fcId);
    
    /**
     * 
     * Description: <br>
     * 查询所有的文件数统计的方法
     * @param sysDate 
     * @return   List<FileCountEntity>
     * @see
     */
    List<FileCountEntity> findAllFc(String sysDate);
    
    /**
     * 
     * Description: <br>
     * 新增一个文件数统计
     * @param fileCountEntity  
     * @return   FileCountEntity  
     * @see
     */
    FileCountEntity addOneFc(FileCountEntity fileCountEntity);
    
    /**
     * 
     * Description: <br>
     * 删除一个文件数统计
     * @param fcId  
     * @see
     */
    void deleteOneFc(Long fcId);
    
    /**
     * 
     * Description: <br>
     * 修改一个文件数统计
     * @param  fileCountEntity  
     * @return FileCountEntity
     * @see
     */
    FileCountEntity editOneFc(FileCountEntity fileCountEntity);

    /**
     * 
     * Description: <br>
     * 导出excel的service
     * @param sysDate  
     * @param response  
     * @exception IOException  
     * @see
     */
    void exportExcel(String sysDate, HttpServletResponse response) throws IOException;
}
