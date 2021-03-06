/*
 * 文件名：TioaTenantChargingShowController.java 
 * 版权：Copyright by www.bonc.com.cn 
 * 描述： 
 * 修改人：leijin
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.week.controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.bonc.nerv.tioa.week.entity.TioaTenantChargingShow;
import com.bonc.nerv.tioa.week.service.FindTioTenChaShoService;
import com.bonc.nerv.tioa.week.service.TioTenChaShoService;

/**

 * @author Jingege
 * @version 2017年8月3日
 * @see TioTenChaShoController
 * @since
 */
@Controller
public class TioTenChaShoController {
    /**
     * 自动注入
     */
    @Autowired
    @Qualifier("tioaTenantChargingShowService")
    private TioTenChaShoService tioTenChaShoService;
    
    /**
     * 自动注入
     */
    @Autowired
    @Qualifier("findTioTenChaShoService")
    private FindTioTenChaShoService findTioTenChaShoService;
    
    /**
     *
     * @param excelFile  
     * @return ""
     * @see
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveToDB(@RequestParam(value = "upload") MultipartFile excelFile) {
        try {
            tioTenChaShoService.deleteAll();
            tioTenChaShoService.saveExcel(excelFile);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return JSON.toJSONString("出现错误！");
        }
        return JSON.toJSONString("导入数据库成功！");
    }

    /**
     * @return  index
     * @see
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
    
    /**
     * 根据TcId查询记录
     * @param id id
     * @return ""
     * @see
     */
    @RequestMapping(value = "/findByTcId")
    @ResponseBody
    public String findByTcId(@RequestParam("jstcId") Long id){
        TioaTenantChargingShow tiao = findTioTenChaShoService.findTioTenChaShoByTcId(id);
        return JSON.toJSONString(tiao);
    }
    
    /**
     * 查询所有记录
     * @return ""
     * @see
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public Object findAll(){
        List<TioaTenantChargingShow> list = findTioTenChaShoService.findAllTioTenChaSho();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data",list);
        return (JSON)JSON.toJSON(map);
    }
    
    /**
     * 根据服务类型查询记录
     * @return ""
     * @see
     */
    @RequestMapping(value = "/findByServiceType")
    @ResponseBody
    public Object findByServiceType(){
        List<TioaTenantChargingShow> list =  findTioTenChaShoService.findTioTenByServiceType(10);
        return JSON.toJSON(list);
    }
    


    /**
     * Description: 保存新增的数据
     * @param data  
     * @return ""
     * @see
     */
    @RequestMapping("/saveNewData")
    @ResponseBody
    public String saveNewData(@Valid TioaTenantChargingShow data){

        try {
            tioTenChaShoService.save(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString("保存新增数据成功！");

    }

    /**
     * Description: 保存修改后的数据，请求的数据应包含tcId项
     * 
     * @param data  
     * @return ""
     * @see
     */
    @RequestMapping("/saveModifyData")
    @ResponseBody
    public String saveModifyData(@Valid TioaTenantChargingShow data) {
        try {
            tioTenChaShoService.save(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString("保存修改数据成功！");

    }

    /**
     * Description: 根据id删除记录
     * 
     * @param id  
     * @return ""
     * @see
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam("tcId") Long id) {
        try {
            tioTenChaShoService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString("数据删除成功！");

    }
    
    
    /**
     * Description:将表中数据保存到execl文件。
     * @param request 请求的request对象
     * @param response  响应的response对象
     * @see 
     */
    @RequestMapping("/savaToFile")
    @ResponseBody
    public void savaToFile(HttpServletRequest request, HttpServletResponse response){
        tioTenChaShoService.savaToFile(request, response);
    }
    
    /**
     * 根据是否近期、租户类型来查询
     * @param flag 是否近期 
     * @param tenantType tenantType
     * @return ""
     * @throws SQLException  SQLException
     * @throws ClassNotFoundException  ClassNotFoundException
     * @see
     */
    @RequestMapping(value = "/jffindByTypeAndDate")
    @ResponseBody
    public Object findByTypeAndDate(@RequestParam("flag") Boolean flag,
                                    @RequestParam("tenantType") Integer tenantType) throws ClassNotFoundException, SQLException{
         
       
        List<TioaTenantChargingShow> list = tioTenChaShoService.findByTypeAndDate(flag,tenantType);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data",list);
        return (JSON)JSON.toJSON(map);
    }
    
    
}