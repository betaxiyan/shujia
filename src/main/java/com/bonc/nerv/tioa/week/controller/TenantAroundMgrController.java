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
    @RequestMapping("/tenantAroundMgr")
    public String tenantAroundMgr(){
        return "manage/tenant_arount_show";
    }
    
    /**
     * 将id和name从接口导入到数据库
     * @return "" 
     * @see
     */
    @RequestMapping("/tenantAroundMgrController")
    @ResponseBody
    public String getTenantAroundMgr(){
        tenantAroundMgrService.saveIdAndNameFromHttp();
        return JSON.toJSONString("");
    }
    
    /**
     * 从数据库导出到Excel进行批量修改
     * @return ""
     * @see
     */
    @RequestMapping("/exportFromTenantAroundMgr")
    @ResponseBody
    public String exportFromTenantAroundMgr(){
        tenantAroundMgrService.exportFromTenantAroundMgr();
        return JSON.toJSONString("从数据库导出Excel成功");
    }
    
    /**
     * 对Excel进行批量修改后导入数据库
     * @return “”
     * @see
     */
    @RequestMapping("/importToTenantAroundMgr")
    @ResponseBody
    public String importToTenantAroundMgr() {
        
        return JSON.toJSONString("导入Excel到数据库成功");
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
     * 保存一条记录到数据库
     * @param tioaTenantAroundShowEntity  
     * @return ""
     * @see
     */
    @RequestMapping(value={ "/manage/saveTenantAroundMgr"} , method = RequestMethod.POST)
    @ResponseBody
    public String saveTenantAroundMgr(TioaTenantAroundShowEntity tioaTenantAroundShowEntity){
        Map<String, Object> map = new HashMap<String, Object>();
        tenantAroundMgrService.saveTenantAroundMgr(tioaTenantAroundShowEntity);
        map.put("message", "200");
        return JSON.toJSONString(map);
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
   @ResponseBody
   public String saveToDB(@RequestParam(value = "upload") MultipartFile excelFile) {
       try {
           tenantAroundMgrService.saveExcel(excelFile);
       } catch (ParseException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return JSON.toJSONString("导入数据库成功！");
   }

}
