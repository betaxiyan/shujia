/*
 * 文件名：AccountFilecountMidEntityDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonc.nerv.tioa.entity.AccountFilecountMidEntity;

@Transactional
public interface AccountFilecountMidEntityDao extends JpaRepository<AccountFilecountMidEntity, Long>
{

}
