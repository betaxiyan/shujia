/*
 * 文件名：ReadTxtController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;
import com.bonc.nerv.tioa.week.service.ExcelAnalyseService;

@Controller
public class ReadTxtController {
    
    @Autowired
    private ExcelAnalyseService excelAnalyseService;

    @RequestMapping(value = "/readTxt")
    @ResponseBody
    public String readTxt(){
        List<ResourceUsageMidEntity> list = excelAnalyseService.analyseHbaseText("F:\\吕涛.txt");
        excelAnalyseService.hbaseToDb(list);
        return JSON.toJSONString("保存到数据库成功");
    }

}
