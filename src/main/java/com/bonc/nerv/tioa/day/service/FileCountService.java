/*
 * 文件名：FileCountService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.day.service;

import java.util.List;

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
     * @return   FileCountEntity  
     * @see
     */
    FileCountEntity addOneFc(FileCountEntity fileCountEntity);
    
    /**
     * 
     * Description: <br>
     * 删除一个文件数统计
     * @see
     */
    void deleteOneFc(Long fcId);
    
    /**
     * 
     * Description: <br>
     * 修改一个文件数统计
     * @return FileCountEntity
     * @see
     */
    FileCountEntity editOneFc(FileCountEntity fileCountEntity);
}
