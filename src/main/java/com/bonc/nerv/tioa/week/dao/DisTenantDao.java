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

    /*
     * 查询数据总和
     */
    @Query("select count(dt) from DisTenantEntity dt where dt.tdId = ?1")
    int findById(long tdId);

    /*
     * 查询每一条数据
     */
    @Query("select dt from DisTenantEntity dt where dt.tdId = ?1")
    DisTenantEntity findOne(long tdId);
    
}
