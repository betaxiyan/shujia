package com.bonc.nerv.tioa.week.dao;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;


/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yuanpeng
 * @version 2017年7月31日
 * @see TioaDao
 * @since
 */
@Transactional
public interface TioaDao {
    
    /**
     * Description: 返回一个集合，供SavaToExeclUtil调用
     * @return List 集合
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see 
     */
    List<List<Object>> getAllTioa() throws ClassNotFoundException, SQLException;
}
