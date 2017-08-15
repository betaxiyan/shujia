/*
 * 文件名：TenantAroundMgrController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.week.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;
import com.bonc.nerv.tioa.week.service.TenantAroundMgrService;

/**
 * 
 * 租户周边信息管理控制器
 * @author yuanpeng
 * @version 2017年8月6日
 * @see TenantAroundMgrController
 * @since
 */
@Controller
public class TenantAroundMgrController {
    /**
     * 
     */
    @Autowired
    @Qualifier("tenantAroundMgrService")
    private TenantAroundMgrService tenantAroundMgrService;
    
    /**
     * 显示页面
     * @return "tenant_arount_show"
     */
    @RequestMapping(value ={"/tenantAroundMgr" , "/manager"})
    public String tenantAroundMgr(){
        tenantAroundMgrService.saveIdAndNameFromHttp();
        return "manage/tenant_arount_show";
    }
    
    
    
    
    /**
     * 获取所有租户周边信息管理信息
     * @return map
     * @see
     */
    @RequestMapping("manage/findAllTenantAroundMgr")
    @ResponseBody
    public Map<String,Object> findAllTenantAroundMgr(){
        List<TioaTenantAroundShowEntity> list = tenantAroundMgrService.findAllTenantAroundMgr();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("draw","1");
        map.put("recordsFiltered", list.size());
        map.put("recordsTotal", list.size());
        map.put("data", list);
        return map;
    }
    
    /**
     * 
     * 判断是否删除一条数据
     * 
     * @param ttaId
     * @return 
     * @see
     */
    @RequestMapping("/manage/validateById")
    @ResponseBody
    public Boolean validateById(Long ttaId){
        return tenantAroundMgrService.validateById(ttaId);
        
    }
    
    /**
     * 删除一条数据 
     * @param ttaId   
     * @return ""
     * @see
     */
    @RequestMapping("/manage/deleteTenantAroundMgr")
    public String deleteTenantAroundMgr(Long  ttaId){
        tenantAroundMgrService.deleteTenantAroundMgr(ttaId);
        return "manage/tenant_arount_show";
    }
    
    /**
     * 获取修改数据
     * 
     * @param ttaId
     * @return 
     * @see
     */
    @RequestMapping("/manage/update")
    @ResponseBody
    public TioaTenantAroundShowEntity updateTenantAroundMgr(Long ttaId){
        TioaTenantAroundShowEntity tioaTenAro =  tenantAroundMgrService.updateTenantAroundMg(ttaId);
       System.out.println(tioaTenAro);
        return tioaTenAro;
        
    }
    
    /**
     * 保存一条记录到数据库
     * @param tioaTenantAroundShowEntity  
     * @return ""
     * @see
     */
    @RequestMapping(value={ "/manage/saveTenantAroundMgr"} , method = RequestMethod.POST)
    public String saveTenantAroundMgr(TioaTenantAroundShowEntity tioaTenantAroundShowEntity){
        tenantAroundMgrService.saveTenantAroundMgr(tioaTenantAroundShowEntity);
        return "redirect:/tenantAroundMgr";
    }
    
    /**
     * 导出租户周边信息管理情况表
     * @param response 
     * @see
     */
    @RequestMapping(value={"/manange/exportTenantAroundMgr"})
    @ResponseBody
    public void exportTenretired(HttpServletResponse response){
        List<TioaTenantAroundShowEntity> list=tenantAroundMgrService.exportTenretired();
        tenantAroundMgrService.getExcel(list, response);
    }
    
    /**
    *
    * @param excelFile  
    * @return ""
    * @see
    */
   @RequestMapping(value = "/manage/saveTenantAroundMgrExcel")
   public String saveToDB(@RequestParam(value = "upload") MultipartFile excelFile) {
       tenantAroundMgrService.deleteAll();
       try {
           tenantAroundMgrService.saveExcel(excelFile);
       } catch (ParseException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return "redirect:/tenantAroundMgr";
   }
   
}
