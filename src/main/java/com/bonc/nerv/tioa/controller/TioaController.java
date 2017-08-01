package com.bonc.nerv.tioa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.nerv.tioa.service.ExternalRestService;
import com.bonc.nerv.tioa.service.impl.ExternalRestServiceImpl;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yuanpeng
 * @version 2017年7月31日
 * @see TioaController
 * @since
 */
@Controller
public class TioaController {
    
    @Autowired
    private ExternalRestService externalRestService;
    @RequestMapping("/resAccToDb")
    @ResponseBody
    public void resAccToDb(){
        externalRestService.resAccToDb();
    }
    
    @RequestMapping("/accFcountToDb")
    @ResponseBody
    public void accFcountToDb(@RequestParam("sysDate") String sysDate) {
        externalRestService.accFcountToDb(sysDate);
    }
}
