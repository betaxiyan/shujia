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

/**
 * 租户资源中间表JPA
 * @author zhiyong
 * @version 2017年8月3日
 * @see TenantResourceMidDao
 * @since
 */
@Transactional
public interface TenantResourceMidDao extends JpaRepository<TenantResourceMidEntity, Long>{
    
    /**
     * 
     * @param enDate 退租时间
     * @return  list集合
     * @see
     */
    @Query("select ten from TenantResourceMidEntity ten where ten.isInvalid = 'invalid' and ten.endRentDate >:endrentdate")
    List<TenantResourceMidEntity> findByEndRentDate(@Param(value = "endrentdate")Date enDate);
}
