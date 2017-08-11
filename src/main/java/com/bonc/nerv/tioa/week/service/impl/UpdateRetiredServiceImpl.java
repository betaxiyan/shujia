/*
 * 文件名：UpdateRetiredServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月11日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.TenantResourceMidDao;
import com.bonc.nerv.tioa.week.dao.TenretiredDao;
import com.bonc.nerv.tioa.week.entity.TenantResourceMidEntity;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;
import com.bonc.nerv.tioa.week.service.TenantAroundMgrService;
import com.bonc.nerv.tioa.week.service.TenantResourceService;
import com.bonc.nerv.tioa.week.service.UpdateRetiredService;

/**
 * 
 * 刷新租户退租数据服务类
 * @author zhiyong
 * @version 2017年8月11日
 * @see UpdateRetiredServiceImpl
 * @since
 */
@Service("updateRetiredService")
public class UpdateRetiredServiceImpl implements UpdateRetiredService{
    
    /**
     * TenantResourceMidDao
     */
    @Autowired
    private TenantResourceMidDao tenantResourceMidDao;
    
    /**
     * TenretiredDao
     */
    @Autowired
    private TenretiredDao tenretiredDao;
    
    /**
     * TenantAroundMgrService
     */
    @Autowired 
    private TenantAroundMgrService tenantAroundMgrService;
    
    /**
     * TenantResourceService
     */
    @Autowired
    private TenantResourceService tenantResourceService;
    
    /**
     * 刷新租户退租数据
     * @return 是否刷新成功
     */
    public Boolean updateRetired() {
        Boolean result = false;
        
        tenantAroundMgrService.saveIdAndNameFromHttp();//刷新租户周围数据
        tenantResourceService.tenResToDb();//刷新租户资源数据
        Date maxDate = tenretiredDao.maxTime();
        List<TenantResourceMidEntity> teResourceList = tenantResourceMidDao.findByEndRentDate(maxDate);
        List<TioaTenantAroundShowEntity> aroundList = tenantAroundMgrService.findAllTenantAroundMgr();
        List<TenretiredEntity> tenretiredList = new ArrayList<>();
        TenretiredEntity tenretiredEntity =null;
        for (int i=0; i<aroundList.size(); i++) {
            for (int j=0;j<teResourceList.size();j++) {
                if(aroundList.get(i).getTenantId().equals(teResourceList.get(j).getTenantId())) {
                    tenretiredEntity = new TenretiredEntity();
                    tenretiredEntity.setTenantName(aroundList.get(i).getTenantName());
                    tenretiredEntity.setServiceType(teResourceList.get(j).getServiceType());
                    tenretiredEntity.setTenantLevel(aroundList.get(i).getTenantLevel());
                    tenretiredEntity.setTenantBoss(aroundList.get(i).getTenantBoss());
                    tenretiredEntity.setTenantTel(aroundList.get(i).getTenantTel());
                    tenretiredEntity.setResourceType(teResourceList.get(j).getTypeName());
                    tenretiredEntity.setAskIp(teResourceList.get(j).getIpAddr());
                    tenretiredEntity.setStorage(teResourceList.get(j).getStorage());
                    tenretiredEntity.setComputeRoom(teResourceList.get(j).getComputeRoom());
                    tenretiredEntity.setUniplatformNum(aroundList.get(i).getNumOfUnifiedPlatform());
                    tenretiredEntity.setNumOf4a(aroundList.get(i).getNumOf4a());
                    tenretiredEntity.setDemand(aroundList.get(i).getTenantReqirement());
                    tenretiredEntity.setServiceName(teResourceList.get(j).getServiceName());
                    tenretiredEntity.setSequenceName(teResourceList.get(j).getSequenceName());
                    tenretiredEntity.setAskDate(teResourceList.get(j).getAskDate());
                    tenretiredEntity.setOpenDate(teResourceList.get(j).getOpenDate());
                    tenretiredEntity.setEndRentDate(teResourceList.get(j).getEndRentData());
                    tenretiredEntity.setTenantInterface(aroundList.get(i).getTenantInterface());
                    tenretiredList.add(tenretiredEntity);
                }
            }
        }
        if(tenretiredList!=null) {
            tenretiredDao.save(tenretiredList);
            result=true;
        }
        return result;
    }

}
