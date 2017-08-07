/*
 * 文件名：TenantAroundMgrDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月7日
 */

package com.bonc.nerv.tioa.week.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;

public interface TenantAroundMgrDao extends CrudRepository<TioaTenantAroundShowEntity,Long>{
    TioaTenantAroundShowEntity findTioaTenantAroundShowEntityByTenantId(Long tenantId);
    
    /**
     * 从数据库获取所有记录
     * return list
     */
    List<TioaTenantAroundShowEntity> findAll();
}
