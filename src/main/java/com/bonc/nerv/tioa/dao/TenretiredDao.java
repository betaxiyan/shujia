/*
 * 文件名：TenretiredDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年7月28日
 */

package com.bonc.nerv.tioa.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bonc.nerv.tioa.entity.TenretiredEntity;


@Repository
public interface TenretiredDao extends CrudRepository<TenretiredEntity, Long>,JpaSpecificationExecutor<TenretiredEntity> {
   
    Page<TenretiredEntity> findAll(Specification<TenretiredEntity> querySpecifi, Pageable pageable);
    
    List<TenretiredEntity> findAll();
    
    @Modifying
    @Transactional
    @Query("select count(ten) from TenretiredEntity ten where ten.tlId = ?1")
    int findByTlId(long tlId);
}
