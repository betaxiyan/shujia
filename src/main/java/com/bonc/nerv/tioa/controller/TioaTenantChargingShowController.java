/*
 * 文件名：TioaTenantChargingShowController.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：Jingege
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.controller;


import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.bonc.nerv.tioa.entity.TioaTenantChargingShow;
import com.bonc.nerv.tioa.service.TioaTenantChargingShowService;


@Controller
public class TioaTenantChargingShowController {
    @Autowired
    @Qualifier("tioaTenantChargingShowService")
    private TioaTenantChargingShowService tioaTenantChargingShowService;

    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveToDB(@RequestParam(value = "upload") MultipartFile excelFile) {
        try {
            tioaTenantChargingShowService.save(excelFile);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return JSON.toJSONString("导入数据库成功！");
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    /**
     * Description: 保存新增的数据
     * 
     * @param data
     * @see
     */
    @RequestMapping("/saveNewData")
    @ResponseBody
    public String saveNewData(@Valid TioaTenantChargingShow data){

        try {
            tioaTenantChargingShowService.save(data);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return JSON.toJSONString("保存新增数据成功！");

    }

    /**
     * Description: 保存修改后的数据，目前和保存新增数据一样
     * 
     * @param data
     * @return
     * @see
     */
    @RequestMapping("/saveModifyData")
    @ResponseBody
    public String saveModifyData(@Valid TioaTenantChargingShow data) {
        try {
            tioaTenantChargingShowService.save(data);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return JSON.toJSONString("保存修改数据成功！");

    }

    /**
     * Description: 根据id删除记录
     * 
     * @param id
     * @return
     * @see
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam("id") Long id) {
        try {
            tioaTenantChargingShowService.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return JSON.toJSONString("数据删除成功！");

    }
}
