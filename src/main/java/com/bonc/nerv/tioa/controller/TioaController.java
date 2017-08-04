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
 * @author yuanpeng
 * @version 2017年7月31日
 * @see TioaController
 * @since
 */
@Controller
public class TioaController {
    
    /**
     * 声明调用外部restful接口的服务类
     */
    @Autowired
    private ExternalRestService externalRestService;
    
    
    /**
     * 
     * Description: <br>
     * 进入实施自动化展示页面
     * @return 
     * @see
     */
    @RequestMapping("/view")
    public String inter(){
        return "view/data_view";
    }
    
    /**
     * 
     * Description: <br>
     * 进入实施自动化展示页面
     * @return 
     * @see
     */
    @RequestMapping("/view2")
    public String inter2(){
        return "fragment/charging_frg";
    }
    
    
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
