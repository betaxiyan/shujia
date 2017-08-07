/*
 * 文件名：FileCountController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.day.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonc.nerv.tioa.day.entity.FileCountEntity;
import com.bonc.nerv.tioa.day.service.FileCountService;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yuanpeng
 * @version 2017年8月7日
 * @see FileCountController
 * @since
 */
@Controller
public class FileCountController {
    
    @Autowired
    private FileCountService fileCountService;
    
    /**
     * 
     * Description: <br>
     * 查询所有的文件数统计
     *  
     * @see
     */
    @RequestMapping(value = {"/findAllFileCount"})
    public void findAllFileCount(String sysDate){
        fileCountService.findAllFc(sysDate);
    }

    /**
     * 
     * Description: <br>
     * 删除一条数据
     * @see
     */
    public void deleteOne(String fcId){
        
    }
    
    /**
     * 
     * Description: <br>
     * 新增一条数据
     * @param fileCountEntity 
     * @see
     */
    public void addOne(FileCountEntity fileCountEntity){
        
    }
    
    /**
     * 
     * Description: <br>
     * 修改一条数据
     * 
     * @param fileCountEntity 
     * @see
     */
    public void editOne(FileCountEntity fileCountEntity){
        
    }
}
