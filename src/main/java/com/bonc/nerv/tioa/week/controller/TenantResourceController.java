/*
 * 文件名：TenantResourceController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月3日
 */

package com.bonc.nerv.tioa.week.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bonc.nerv.tioa.week.service.TenantResourceService;


/**
 * 调用接口获取我的资源数据写入数据库
 * @author zhiyong
 * @version 2017年8月3日
 * @see TenantResourceController
 * @since
 */
@Controller
public class TenantResourceController {
    
    /**
     * TenantResourceService
     */
    @Autowired
    private TenantResourceService tenantResourceService;
    
    /**
     * 
     * Description: <br>
     * 调用接口获取我的资源数据写入数据库
     * @return 执行结果提示信息
     * @see
     */
    @RequestMapping("/tenResToDb")
    @ResponseBody
    public String tenResToDb() {
        tenantResourceService.tenResToDb();
        return  JSON.toJSONString("获取我的资源数据导入数据库成功！");
    }
}
