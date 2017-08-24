/*
 * 文件名：TenantResourceMidDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月3日
 */

package com.bonc.nerv.tioa.week.dao;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bonc.nerv.tioa.week.entity.TenantResourceMidEntity;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;

/**
 * 租户资源中间表JPA
 * @author zhiyong
 * @version 2017年8月3日
 * @see TenantResourceMidDao
 * @since
 */
@Transactional
public interface TenantResourceMidDao extends JpaRepository<TenantResourceMidEntity, String>{
    
    /**
     * 
     * @param enDate 退租时间
     * @return  list集合
     * @see
     */
    @Query("select ten from TenantResourceMidEntity ten where ten.isInvalid = 'invalid' and ten.endRentDate >:endrentdate")
    List<TenantResourceMidEntity> findByEndRentDate(@Param(value = "endrentdate")Date enDate);
    
    /**
     * @return  list集合
     * @see
     */
    @Query("select ten from TenantResourceMidEntity ten where ten.isInvalid = 'invalid'")
    List<TenantResourceMidEntity> findByEndRentDateNull();
    
    /**
     * 
     * Description: <br>
     * 租户资源表和租户信息表联合查询
     * @return List<TenretiredEntity>
     * @see
     */
    @Query("select new com.bonc.nerv.tioa.week.entity.TenretiredEntity("
        + "t.tenantId, a.tenantName, t.serviceType,"
        +"a.tenantLevel, a.tenantBoss, a.tenantTel,"
        +"t.typeName, t.ipAddr, t.storage,"
        +"t.computeRoom, a.tenantReqirement,"
        +"t.serviceName, t.sequenceName, t.rresId, t.askDate,"
        +"t.openDate, t.endRentDate, a.tenantInterface) "
        + " from TenantResourceMidEntity t , TioaTenantAroundShowEntity a "
        + " where t.tenantId = a.tenantId and t.state is null or t.state = 0")
    //@Query("select t from TenretiredEntity t")
    List<TenretiredEntity> fixTrmAndTam();
    
}
