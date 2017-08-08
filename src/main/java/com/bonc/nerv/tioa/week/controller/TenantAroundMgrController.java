/*
 * 文件名：TenantAroundMgrController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.week.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String deleteTenantAroundMgr(Long  ttaId){
        tenantAroundMgrService.deleteTenantAroundMgr(ttaId);
        return JSON.toJSONString("删除成功");
    }
    
    /**
     * 保存一条记录到数据库
     * @param tioaTenantAroundShowEntity  
     * @return ""
     * @see
     */
    @RequestMapping("/manage/saveTenantAroundMgr")
    @ResponseBody
    public String saveTenantAroundMgr(TioaTenantAroundShowEntity tioaTenantAroundShowEntity){
        tenantAroundMgrService.saveTenantAroundMgr(tioaTenantAroundShowEntity);
        return JSON.toJSONString("修改成功");
    }

}
