/*
 * 文件名：FindTioTenChaShoService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月2日
 */

package com.bonc.nerv.tioa.week.service;

import java.util.List;

import com.bonc.nerv.tioa.week.entity.TioaTenantChargingShow;

/**
 * 1、查询全部数据  参数：无
 * 2、根据条件“服务类型”查询数据
 * 3、根据id查询一条数据
 * @author Jingege
 * @version 2017年8月2日
 * @see FindTioTenChaShoService
 * @since
 */
public interface FindTioTenChaShoService {
    
    /**
     * 查询全部数据  参数：无
     * @return list
     * @see
     */
    List<TioaTenantChargingShow> findAllTioTenChaSho();
    
    /**
     * 根据条件“服务类型”查询数据
     * @param serviceType 
     * @return list
     * @see
     */
    List<TioaTenantChargingShow> findTioTenByServiceType(Integer serviceType);
    
    /**
     * 根据id查询一条数据
     * @param tcId 
     * @return list
     * @see
     */
    TioaTenantChargingShow findTioTenChaShoByTcId(Long tcId);
    
}
