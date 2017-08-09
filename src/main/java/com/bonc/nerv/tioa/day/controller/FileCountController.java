/*
 * 文件名：FileCountController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.day.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bonc.nerv.tioa.day.entity.FileCountEntity;
import com.bonc.nerv.tioa.day.service.FileCountService;

/**
 * 
 * 日报文件统计控制器
 * @author yuanpeng
 * @version 2017年8月7日
 * @see FileCountController
 * @since
 */
@Controller
public class FileCountController {
    
    /**
     * FileCountService
     */
    @Autowired
    private FileCountService fileCountService;
    
    /**
     * 
     * Description: <br>
     * 跳转到日报页面
     * @return JSON
     * @see
     */
    @RequestMapping(value = {"/day/inter"})
    public String inter(){
        return "view/fcount_view";
    }
    
    /**
     * 
     * Description: <br>
     * 查询所有的文件数统计
     * @param sysDate  
     * @return JSON
     * @see
     */
    @RequestMapping(value = {"/fcount/findAllFileCount"})
    @ResponseBody
    public String findAllFileCount(String sysDate){
        Map<String,Object> map = new HashMap<String, Object>();
        List<FileCountEntity> fileCountEntities = fileCountService.findAllFc(sysDate);
        map.put("message", "200");
        map.put("fileCountList", fileCountEntities);
        return JSON.toJSONString(map);
        
    }

    /**
     * 
     * Description: <br>
     * 查询一个文件统计
     * @param fcId  id
     * @return  JSON
     * @see
     */
    @RequestMapping(value = {"/fcount/findOneFc"})
    @ResponseBody
    public String findOneFc(Long fcId){
        Map<String,Object> map = new HashMap<String, Object>();
        FileCountEntity fileCountEntity = fileCountService.findOneFc(fcId);
        map.put("message", "200");
        map.put("fileCountEntity", fileCountEntity);
        return JSON.toJSONString(map);
        
    }
    
    /**
     * 
     * Description: <br>
     * 删除一条数据
     * @param fcId  
     * @return JSON
     * @see
     */
    @RequestMapping(value = {"/fcount/deleteOne"})
    @ResponseBody
    public String deleteOne(Long fcId){
        Map<String,Object> map = new HashMap<String, Object>();
        fileCountService.deleteOneFc(fcId);
        map.put("message", "200");
        return JSON.toJSONString(map);
    }
    
    /**
     * 
     * Description: <br>
     * 新增一条数据
     * @param fileCountEntity 
     * @return JSON
     * @see
     */
    @RequestMapping(value = {"/fcount/addOne"}, method = RequestMethod.POST)
    @ResponseBody
    public String addOne(FileCountEntity fileCountEntity){
        Map<String,Object> map = new HashMap<String, Object>();
        fileCountService.addOneFc(fileCountEntity);
        map.put("message", "200");
        return JSON.toJSONString(map);
    }
    
    /**
     * 
     * Description: <br>
     * 修改一条数据
     * @param fileCountEntity 
     * @return JSON
     * @see
     */
    @RequestMapping(value = {"/fcount/editOne"}, method = RequestMethod.POST)
    @ResponseBody
    public String editOne(FileCountEntity fileCountEntity){
        Map<String,Object> map = new HashMap<String, Object>();
        fileCountService.editOneFc(fileCountEntity);
        map.put("message", "200");
        return JSON.toJSONString(map);
    }
    
    /**
     * 
     * Description: <br>
     * 导出excel
     * @param sysDate 
     * @param response  
     * @see
     */
    @RequestMapping(value = {"/fcount/exportExcel"}, method = RequestMethod.GET)
    public void exportExcel(String sysDate, HttpServletResponse response){
        try {
            fileCountService.exportExcel(sysDate, response);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
