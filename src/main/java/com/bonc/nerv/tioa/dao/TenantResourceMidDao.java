/*
 * 文件名：TenantResourceMidDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月3日
 */

package com.bonc.nerv.tioa.dao;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bonc.nerv.tioa.entity.TenantResourceMidEntity;

/**
 * 租户资源中间表JPA
 * @author zhiyong
 * @version 2017年8月3日
 * @see TenantResourceMidDao
 * @since
 */
@Transactional
public interface TenantResourceMidDao extends JpaRepository<TenantResourceMidEntity, Long>{

}
