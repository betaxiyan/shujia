/*
 * 文件名：TioaTenantChargingShowDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bonc.nerv.tioa.entity.TioaTenantChargingShow;

public interface TioaTenantChargingShowDao extends CrudRepository<TioaTenantChargingShow, Long>{
    /*
     * 查询所有数据
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
   @Override
List<TioaTenantChargingShow> findAll();
   
   /*
    * 根据服务类型查询数据
    */
   List<TioaTenantChargingShow> findByServiceType(Integer serviceType);
       
   /*
    * 根据Id查询一条数据
    */
   TioaTenantChargingShow findByTcId(Long tcId);
   
}
