/*
 * 文件名：TenretiredDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年7月28日
 */

package com.bonc.nerv.tioa.week.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bonc.nerv.tioa.week.entity.TenretiredEntity;

/**
 * 
 * 已退租户情况dao层
 * @author ymm
 * @version 2017年8月2日
 * @see TenretiredDao
 * @since
 */
@Transactional
public interface TenretiredDao extends CrudRepository<TenretiredEntity,Long>,JpaSpecificationExecutor<TenretiredEntity> {
    
    /**
     * 租户列表分页条件查询
     * @param pageable 分页实体对象
     * @param querySpecifi 查询条件
     * @return 分页租户信息
     */
    Page<TenretiredEntity> findAll(Specification<TenretiredEntity> querySpecifi, Pageable pageable);
    
    /**
     * 查询所有已退租户
     * @return 所有租户
     */
    List<TenretiredEntity> findAll();
    
    /**
     * 
     * Description: 根据tlId查询租户记录
     * @param tlId 表id
     * @return  租户记录数
     * @see
     */
    @Query("select count(ten) from TenretiredEntity ten where ten.tlId = ?1")
    int findByTlId(long tlId);
    
    /**
     * 
     * 查询退租记录中最晚退租时间
     * 
     * @return 
     * @see
     */
    @Query("select max(endRentDate) from TenretiredEntity")
    Date maxTime();
    
    /**
     * 
     * Description: <br>
     * 联合查询
     * @return List<TenretiredEntity>
     * @see
     */
    @Query("select new com.bonc.nerv.tioa.week.entity.TenretiredEntity ("
        + "t.tlId, t.tenantId, a.tenantName, t.serviceType, a.tenantLevel,"
        +"a.tenantBoss, a.tenantTel, t.resourceType, t.askIp,"
        +"t.hostNum, t.storage, t.computingResourceRate,"
        +"t.computeRoom,"
        +"a.tenantReqirement, t.serviceName, t.sequenceName, t.askDate,"
        +"t.openDate, t.changeDate, t.endRentDate, t.rresId, "
        +"a.tenantInterface, t.remark)"
        + " from TenretiredEntity t , TioaTenantAroundShowEntity a "
        + "where t.tenantId = a.tenantId")
    List<TenretiredEntity> findRefreshTenretired();
    
    /**
     * 计算4A或统一数量
     * @param tenantid 租户ID
     * @param resource 资源类型
     * @return 数量
     * @see
     */
    @Query("select count(ten) from TenretiredEntity  ten "
        +"where ten.tenantId = ?1 and ten.resourceType = ?2")
    int countType(String tenantid,String resource);
}
