/*
 * 文件名：ExternalRestController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月4日
 */

package com.bonc.nerv.tioa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.nerv.tioa.service.ExternalRestService;

/**
 * 
 * 调用外部restful接口的服务类
 * @author zhiyong
 * @version 2017年8月4日
 * @see ExternalRestController
 * @since
 */
@Controller
public class ExternalRestController {
    
    /**
     * 声明调用外部restful接口的服务类
     */
    @Autowired
    private ExternalRestService externalRestService;
    
    /**
     * 
     * 调用接口获取资源和账号数据写入数据库中
     * @see
     */
    @RequestMapping("/resAccToDb")
    @ResponseBody
    public void resAccToDb(){
        externalRestService.resAccToDb();
    }
    
    /**
     * 
     * Description: <br>
     * 调用接口获取账号和文件数数据写入数据库中
     * @param sysDate 时间参数
     * @see
     */
    @RequestMapping("/accFcountToDb")
    @ResponseBody
    public void accFcountToDb(@RequestParam("sysDate") String sysDate) {
        externalRestService.accFcountToDb(sysDate);
    }
}
