/*
 * 文件名：ExcelExportController.java
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
 * Excel及邮箱控制类
 * @author zhiyong
 * @version 2017年8月8日
 * @see ExcelEmailMgrController
 * @since
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.nerv.tioa.week.service.MergeExcelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 导出Excel
 * @author zhiyong
 * @version 2017年8月8日
 * @see ExcelEmailMgrController
 * @since
 */
@Controller
public class ExcelEmailMgrController {
    
    /**
     * MergeExcelService
     */
    @Autowired
    private MergeExcelService mergeExcelService;
    
    
    /**
     * 导出Excel
     * @param response HttpServletResponse
     * @param request HttpServletResponse
     * @return 操作提示信息
     * @see
     */
    @RequestMapping("/getExcel")
    @ResponseBody
    public String getExcel(HttpServletRequest request, HttpServletResponse response) {
        mergeExcelService.getExcelNew(request,response); 
        return "导出Excel成功！";
    }
    
    @RequestMapping("/isExport")
    @ResponseBody
    public String isExport(Boolean results){
        Boolean isSuccess = false;
        if(results!=null){
            isSuccess = results;
        }
        List<Map<String, Object>> reslist = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        //接口返回的json字符串
        String jsonString="";
        //格式化检查日期的SimpleDateFormat
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Map<String,Object> mapTtl = new HashMap<>();
        String statusTtl = "";
        //调用getMidDateToTtl
        if(isSuccess){
            statusTtl = "导出成功";
        }else{
            statusTtl = "导出失败";
        }
        try {
            Thread.currentThread();
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date date = new Date();
        mapTtl.put("tableName", "totalexport");
        mapTtl.put("updateDate", myFmt.format(date));
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
}
