/*
 * 文件名：CpuMemoryMidDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonc.nerv.tioa.week.entity.CpuMemoryMidEntity;

/**
 * 
 * 获取CPU内存 资源数据
 * @author zhiyong
 * @version 2017年8月14日
 * @see CpuMemoryMidDao
 * @since
 */
@Transactional
public interface CpuMemoryMidDao extends JpaRepository<CpuMemoryMidEntity, Long>{

}
