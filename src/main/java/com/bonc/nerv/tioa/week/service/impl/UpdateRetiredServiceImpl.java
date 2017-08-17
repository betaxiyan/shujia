/*
 * 文件名：UpdateRetiredServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月11日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.TenantResourceMidDao;
import com.bonc.nerv.tioa.week.dao.TenretiredDao;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
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
        //资源表与租户信息表联合查询
        List<TenretiredEntity> tenretiredList = tenantResourceMidDao.fixTrmAndTam();

        if(tenretiredList!=null) {
            tenretiredDao.deleteAll();
            tenretiredDao.save(tenretiredList);
            result=true;
        }
        return result;
    }

}
