/*
 * 文件名：TioaTenantChargingShowDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.week.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bonc.nerv.tioa.week.entity.TioaTenantChargingShow;
/**
 * dao接口
 * @author leijin
 * @version 2017年8月3日
 * @see TioaTenantChargingShowDao
 * @since
 */
public interface TioaTenantChargingShowDao extends CrudRepository<TioaTenantChargingShow, Long>{
    /**
     * 查询所有数据
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    @Override
    List<TioaTenantChargingShow> findAll();
   
   /**
    * 根据服务类型查询数据
    * @param serviceType 服务类型
    * @return list
    */
    List<TioaTenantChargingShow> findByServiceType(Integer serviceType);
       
   /**
    * 根据Id查询一条数据
    * @param tcId id
    * @return TioaTenantChargingShow
    */
    TioaTenantChargingShow findByTcId(Long tcId);
   
}
