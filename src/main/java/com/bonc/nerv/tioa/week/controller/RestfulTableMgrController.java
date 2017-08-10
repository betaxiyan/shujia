/*
 * 文件名：RestfulAndDataController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.week.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.nerv.tioa.week.service.RestfulTableMgrService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Restful接口健康和表数据刷新控制器
 * @author yuanpeng
 * @version 2017年8月6日
 * @see RestfulTableMgrController
 * @since
 */
@Controller
public class RestfulTableMgrController {
    
    /**
     * 浏览接口健康信息的服务层
     */
    @Autowired
    private RestfulTableMgrService restfulTableMgrService;
    
    /**
     * 接口健康信息界面
     * @return health_view
     * @see
     */
    @RequestMapping("/health")
    public String health(){
        
        return  "view/health_view";
    }
    
    /**
     * 调用接口获取接口健康信息
     * @return jsonString
     * @see
     */
    @RequestMapping("/restfulHealth")
    @ResponseBody
    public String restfulHealth(){
        //调用接口健康信息service层
        String jsonString = restfulTableMgrService.restfulHealth();
        return jsonString;
    }
    
    /**
     * 刷新tioa_tenant_leave_show数据表数据
     * @return jsonString
     * @see
     */
    @RequestMapping("/getMidDateToTtl")
    @ResponseBody
    public String getMidDateToTtl(){
        
        List<Map<String, Object>> reslist = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        //接口返回的json字符串
        String jsonString="";
        //格式化检查日期的SimpleDateFormat
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Map<String,Object> mapTtl = new HashMap<>();
        //调用getMidDateToTtl()，用休眠模拟
        try {
            Thread.currentThread();
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date ttl = new Date();
        String statusTtl="Success"; //更新结果应由调用函数返回
        mapTtl.put("tableName", "tioa_tenant_leave_show");
        mapTtl.put("updateDate", myFmt.format(ttl));
        mapTtl.put("result", statusTtl);
        reslist.add(mapTtl);
                
        //封装到data中，返回的json序列格式很重要
        map.put("data", reslist);
        try {
            jsonString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            System.out.println("json序列化异常");
            e.printStackTrace();
        }
        return jsonString;
    }
    
    /**
     * 刷新tioa_tenant_distribute_show数据表数据
     * @return jsonString
     * @see
     */
    @RequestMapping("/getMidDateToTtd")
    @ResponseBody
    public String getMidDateToTtd(){
        
        List<Map<String, Object>> reslist = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        //接口返回的json字符串
        String jsonString="";
        //格式化检查日期的SimpleDateFormat
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Map<String,Object> mapTtd = new HashMap<>();
        //调用getMidDateToTtd(),用休眠模拟，
        try {
            Thread.currentThread();
            Thread.sleep(6000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date ttd = new Date();
        String statusTtd="Success"; //调用函数的结果
        mapTtd.put("tableName", "tioa_tenant_distribute_show");
        mapTtd.put("updateDate", myFmt.format(ttd));
        mapTtd.put("result", statusTtd);
        reslist.add(mapTtd);
                
        //封装到data中,返回的json序列格式很重要
        map.put("data", reslist);
        try {
            jsonString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            System.out.println("json序列化异常");
            e.printStackTrace();
        }
        return jsonString;
    }
}
