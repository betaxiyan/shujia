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
public interface DisTenantDao extends CrudRepository<DisTenantEntity, Long>,JpaRepository<DisTenantEntity, Long>,JpaSpecificationExecutor<DisTenantEntity>{

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
     * 以租户资源表为基础提取化配表的信息
     * 
     * @return List<DisTenantEntity>
     * @see
     */
    @Query("select new com.bonc.nerv.tioa.week.entity.DisTenantEntity( "
                           +"t.serviceType, t.tenantName, t.typeName,"
                           +"t.ipAddr, t.serviceName, t.path, t.sequenceName,"
                           +"t.storage, t.cpuNum, t.memory, t.askDate,"
                           +"t.endRentDate, t.openDate, t.recordId)"
                           + " from TenantResourceMidEntity t where t.isInvalid ='valid'")
    List<DisTenantEntity> basicOfDisTenant();
    
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
                           +"t.endRentDate, t.openDate, t.recordId)"
                           + " from TenantResourceMidEntity t, TioaTenantAroundShowEntity s"
                           + " where t.tenantId = s.tenantId and t.isInvalid ='valid'")
    List<DisTenantEntity> fixPartOfDisTenant();
    
    /**
     * 
     * Description: <br>
     * 租户资源表和yarn表联合查询结果来组装租户已划配表的部分
     * @return List<DisTenantEntity>
     * @see
     */
    @Query("select new com.bonc.nerv.tioa.week.entity.DisTenantEntity("
        + "t.serviceType, t.tenantName,"
        +"t.typeName, t.ipAddr, t.serviceName,"
        +"t.path, t.sequenceName,"
        +"t.storage, t.cpuNum, a.cpuMax, a.cpuAvg,"
        +"t.memory, a.memoryMax, a.memoryAvg,"
        + "t.askDate, t.endRentDate, t.openDate, t.recordId)"
        + " from TenantResourceMidEntity t , CpuMemoryMidEntity a "
        + " where t.sequenceName = a.sequenceName and t.isInvalid ='valid'")
    List<DisTenantEntity> fixTrmAndCmm();
    
    /**
     * 
     * Description: <br>
     * 通过ipAddr和typeName对租户资源表和存储使用大小表进行联合查询
     * @param ip 
     * @param typename 
     * @return String
     * @see
     */
    @Query("select r.storageUsage from ResourceUsageMidEntity r where r.keyword = ?1 and r.type = ?2")
    String findStoUsageByIpAndType(String ip,String typename);
    
    /**
     * 
     * Description: <br>
     * 通过path和typeName对租户资源表和存储使用大小表进行联合查询
     * @param path 
     * @param typename 
     * @return String
     * @see
     */
    @Query("select r.storageUsage from ResourceUsageMidEntity r where r.keyword = ?1 and r.type = ?2")
    String findStoUsageByPathAndType(String path,String typename);
    
    /**
     * 
     * Description: <br>
     * 整合租户资源表和account_filecount_mid表，获取文件数
     * @return List<DisTenantEntity>
     * @see
     */
    @Query("select new com.bonc.nerv.tioa.week.entity.DisTenantEntity("
        + "t.serviceType, t.tenantName,"
        +"t.typeName, t.ipAddr, t.serviceName,"
        +"t.path, t.sequenceName,"
        +"t.storage, t.cpuNum, "
        +"t.memory, a.fileNum,"
        + "t.askDate, t.endRentDate, t.openDate, t.recordId)"
        + " from TenantResourceMidEntity t , AccountFilecountMidEntity a "
        + " where t.sequenceName = a.seqName and t.isInvalid ='valid'")
    List<DisTenantEntity> fixTrmAndAfm();
}
