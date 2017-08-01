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
import org.springframework.stereotype.Repository;

import com.bonc.nerv.tioa.entity.ResourceAccountMidEntity;

@Repository
public interface ResourceAccountMidEntityDao extends JpaRepository<ResourceAccountMidEntity, Long>
{

}
