/*
 * 文件名：ResourceAccountMidEntityDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bonc.nerv.tioa.entity.ResourceAccountMidEntity;

/**
 * 实体类的JPA接口
 * @author zhiyong
 * @version 2017年8月1日
 * @see ResourceAccountMidEntityDao
 * @since
 */
@Transactional
public interface ResourceAccountMidDao extends JpaRepository<ResourceAccountMidEntity, Long> {

}
