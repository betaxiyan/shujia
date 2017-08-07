/*
 * 文件名：FileCountDao.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月7日
 */
package com.bonc.nerv.tioa.day.dao;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bonc.nerv.tioa.day.entity.FileCountEntity;
/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yuanpeng
 * @version 2017年8月7日
 * @see FileCountDao
 * @since
 */
@Transactional
public interface FileCountDao extends JpaRepository<FileCountEntity, Long>{

    /**
     * 
     * Description: <br>
     * 根据系统时间查询
     * @param sysDate 
     * @return List<FileCountEntity>
     * @see
     */
    List<FileCountEntity> findBySysDate(String sysDate);
    
    
}
