package com.bonc.nerv.tioa.week.dao;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import com.bonc.nerv.tioa.week.entity.TioaTenantChargingShow;


/**
 * 因为TioaTenantChargingShowDao没有实现类，也无法写一个实现类，
 * 所以又写了本接口，在其实现类中将文件保存。
 * 
 * @author xiyan
 * @version 2017年8月4日
 * @see TioTenChaSho_2Dao
 * @since
 */
@Transactional
public interface TioTenChaSho_2Dao {
    
    /**
     * Description: 返回一个集合，供SavaToExeclUtil调用
     * @return List 集合
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see 
     */
    List<List<Object>> getAllTioa() throws ClassNotFoundException, SQLException;
    
    /**
     * 根据是否近期、租户类型来查询
     * @param flag 是否近期 
     * @param tenantType tenantType
     * @return ""
     * @throws SQLException  SQLException
     * @throws ClassNotFoundException  ClassNotFoundException
     * @see
     */
    List<TioaTenantChargingShow> findByTypeAndDate(Boolean flag, Integer tenantType) throws ClassNotFoundException, SQLException;
}
