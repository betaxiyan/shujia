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

package com.bonc.nerv.tioa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bonc.nerv.tioa.entity.DisTenantEntity;


public interface DisTenantDao extends CrudRepository<DisTenantEntity, Integer>,JpaRepository<DisTenantEntity, Integer>,JpaSpecificationExecutor<DisTenantEntity>{



    @Query("select count(dt) from DisTenantEntity dt where dt.tdId = ?1")
    public int findById(long tdId);

    @Query("select dt from DisTenantEntity dt where dt.tdId = ?1")
    public DisTenantEntity findOne(long tdId);
    
}
