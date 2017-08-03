/*
 * 文件名：FindTiTeChShServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年8月2日
 */

package com.bonc.nerv.tioa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.dao.TioaTenantChargingShowDao;
import com.bonc.nerv.tioa.entity.TioaTenantChargingShow;
import com.bonc.nerv.tioa.service.FindTioTenChaShoService;
/**
 * 查询tioa_tenant_charging_show表里的记录
 * 1、查询全部数据  参数：无
 * 2、根据条件“服务类型”查询数据
 * 3、根据id查询一条数据
 * @author Jingege
 * @version 2017年8月2日
 * @see FindTiTeChShServiceImpl
 * @since
 */
@Service("findTioTenChaShoService")
public class FindTiTeChShServiceImpl implements FindTioTenChaShoService {

    /**
     * 自动注入
     */
    @Autowired
    private TioaTenantChargingShowDao tioaTenantChargingShowDao;
    
    /**
     * 查询全部数据  参数：无
     * @return list
     * @see
     */
    @Override
    public List<TioaTenantChargingShow> findAllTioTenChaSho() {
        List<TioaTenantChargingShow> list = tioaTenantChargingShowDao.findAll();
        
        return list;
    }
    
    /**
     * 根据条件“服务类型”查询数据
     * @param serviceType
     * @return list
     * @see
     */
    @Override
    public List<TioaTenantChargingShow> findTioTenByServiceType(Integer serviceType) {
        List<TioaTenantChargingShow> list = tioaTenantChargingShowDao.findByServiceType(serviceType);
        
        return list;
    }
    
    /**
     * 根据id查询一条数据
     * @param tcId
     * @return list
     * @see
     */
    @Override
    public TioaTenantChargingShow findTioTenChaShoByTcId(Long tcId) {
        TioaTenantChargingShow tioaTenantChargingShow = tioaTenantChargingShowDao.findByTcId(tcId);
        
        return tioaTenantChargingShow;
    }
    

}
