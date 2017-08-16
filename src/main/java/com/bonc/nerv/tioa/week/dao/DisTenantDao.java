/*
 * 文件名：UserDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Administrator
 * 修改时间：2017年7月28日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bonc.nerv.tioa.week.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bonc.nerv.tioa.week.entity.DisTenantEntity;

/**
 * 
 * DisTenantDao
 * 
 * @author zhangwen
 * @version 2017年8月2日
 * @see DisTenantDao
 * @since
 */
public interface DisTenantDao extends CrudRepository<DisTenantEntity, Integer>,JpaRepository<DisTenantEntity, Integer>,JpaSpecificationExecutor<DisTenantEntity>{

    /**
     * 
     * Description: <br>
     * 通过id查询记录数
     * @param tdId   
     * @return int
     * @see
     */
    @Query("select count(dt) from DisTenantEntity dt where dt.tdId = ?1")
    int findById(long tdId);

    /**
     * 
     * Description: <br>
     * 通过id查询记录
     * @param tdId 
     * @return DisTenantEntity 
     * @see
     */
    @Query("select dt from DisTenantEntity dt where dt.tdId = ?1")
    DisTenantEntity findOne(long tdId);
    
    /**
     * 
     * Description: <br>
     * 通过租户资源表和租户周边信息表查询结果来组装租户已划配表的部分
     * @return List<DisTenantEntity>
     * @see
     */
    @Query("select new com.bonc.nerv.tioa.week.entity.DisTenantEntity(t.serviceType, s.tenantName, s.tenantLevel,"
                           +"s.tenantBoss, s.tenantTel, t.typeName,"
                           +"t.ipAddr, t.serviceName, t.path, t.sequenceName,"
                           +"t.storage, t.cpuNum, t.memory, t.askDate,"
                           +"t.endRentDate, t.openDate)"
                           + " from TenantResourceMidEntity t, TioaTenantAroundShowEntity s "
                           + " where t.tenantId = s.tenantId and t.isInvalid ='valid'")
    List<DisTenantEntity> fixPartOfDisTenant();
    
}
