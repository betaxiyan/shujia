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
}
