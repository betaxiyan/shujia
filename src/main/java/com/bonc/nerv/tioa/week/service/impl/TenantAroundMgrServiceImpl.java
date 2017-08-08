/*
 * 文件名：TenantAroundMgrService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月7日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.TenantAroundMgrDao;
import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;
import com.bonc.nerv.tioa.week.service.TenantAroundMgrService;
import com.bonc.nerv.tioa.week.util.POIUtil;
import com.bonc.nerv.tioa.week.util.WebClientUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 租户周边信息管理服务层实现类
 * @author leijin
 * @version 2017年8月7日
 * @see TenantAroundMgrServiceImpl
 * @since
 */
@Service("tenantAroundMgrService")
public class TenantAroundMgrServiceImpl implements TenantAroundMgrService{

    @Autowired
    private TenantAroundMgrDao tenantAroundMgrDao;
    
    /**
     * 从接口导入数据到数据库
     */
    @Override
    public void saveIdAndNameFromHttp() {
        String jsonStr = WebClientUtil.doGet("http://coptest.bonc.yz/portal/pure/tenant/list",null);
        ObjectMapper map = new ObjectMapper();
        List<TioaTenantAroundShowEntity>list = new ArrayList<TioaTenantAroundShowEntity>();
        try {
            JsonNode jsonNode = map.readTree(jsonStr);
            String success = jsonNode.get("success").toString();
            if (!success.equals("true")) {
                return ;
            } 
            JsonNode dataNode = jsonNode.get("data");
            for (JsonNode nodeOne : dataNode) {
                TioaTenantAroundShowEntity tioaTenantAroundShowEntity = new TioaTenantAroundShowEntity();
                tioaTenantAroundShowEntity.setTenantId(nodeOne.get("tenantId").asText());
                System.out.println(nodeOne.get("tenantId").asText());
                tioaTenantAroundShowEntity.setTenantName(nodeOne.get("tenantName").asText());
                list.add(tioaTenantAroundShowEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //保存到数据库
        tenantAroundMgrDao.save(list);
        System.out.println("保存到数据库成功");
    }
    
    /**
     * 从数据库导出数据到Excel进行批量修改
     */
    public void exportFromTenantAroundMgr(){
        List<TioaTenantAroundShowEntity> list = tenantAroundMgrDao.findAll();
        POIUtil.exportExcel(list);
        System.out.println("导出数据库成功");
    }
    
    /**
     * 将批量修改的Excel导入到数据库
     */
    public void importToTenantAroundMgr(){
        
    }
    
    /**
     * 查询所有记录显示到页面
     * @return list
     */
    public List<TioaTenantAroundShowEntity> findAllTenantAroundMgr(){
        List<TioaTenantAroundShowEntity> list = new ArrayList<TioaTenantAroundShowEntity>();
        list = tenantAroundMgrDao.findAll();
        return list;
    }
}
