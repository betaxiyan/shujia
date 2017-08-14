/*
 * 文件名：ResUsaMidDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：HCN
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;

/**
 * ResourceUsageMidEntity的DAO层
 * @author HCN
 * @version 2017年8月14日
 * @see ResUsaMidDao
 * @since
 */
@Transactional
public interface ResUsaMidDao extends JpaRepository<ResourceUsageMidEntity, Long>{

}
