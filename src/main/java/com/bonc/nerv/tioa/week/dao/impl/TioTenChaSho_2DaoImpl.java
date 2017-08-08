/*
 * 文件名：TioaDaoImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：xiyan
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.week.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.bonc.nerv.tioa.week.dao.TioTenChaSho_2Dao;

@Repository
public class TioTenChaSho_2DaoImpl implements TioTenChaSho_2Dao {
    @Value("${spring.datasource.url}")
    private  String DbUrl;
    @Value("${spring.datasource.username}")
    private  String DbUserName;
    @Value("${spring.datasource.password}")
    private  String password;
    @Value("${spring.datasource.driverClassName}")
    private  String jdbcName;
    
    
    public List<List<Object>> getAllTioa() throws ClassNotFoundException, SQLException {
    
        Class.forName(jdbcName);
        Connection connection = DriverManager.getConnection(DbUrl,DbUserName,password);
   
        StringBuffer sql = new StringBuffer("select * from tioa_tenant_charging_show ");      
        List<List<Object>> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                List<Object> list2 = new ArrayList<>();
                list2.add(rs.getString("tenant_name"));//租户
                list2.add(rs.getInt("service_type"));//服务类型
                list2.add(rs.getInt("tenant_type"));//租户分类
                
                list2.add(rs.getString("resource_time"));//资源
                
                list2.add(rs.getString("uniplatform_4a_time"));//统一及4A
                list2.add(rs.getString("havedate_time"));//数据实际具备
                list2.add(rs.getString("reside_duration"));//入住时长
                list2.add(rs.getInt("monthly_fee"));//月租费用（元/月）
                list2.add(rs.getFloat("data_fee"));//数据报价/万元
                list2.add(rs.getString("begin_rent_date"));//起租日期
                list2.add(rs.getString("taste_duration"));//试用期
                list2.add(rs.getString("charge_begin_date"));//计费时期
                list2.add(rs.getString("charge_end_date"));//到期日期
                list2.add(rs.getString("introduce_unicom"));//联通引入方
                list2.add(rs.getString("introduce_tenant"));//引入方联系人（租户管理员）
                list2.add(rs.getString("contact"));//联系方式
                list2.add(rs.getString("ask_date"));//申请日期
                list2.add(rs.getInt("sign_contract"));//是否签署合同
                list2.add(rs.getString("monthly_fee_remark"));//月租备注
                list2.add(rs.getString("end_rent_date"));//退租时间
                list2.add(rs.getString("remark"));//备注
                
                list.add(list2);
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        connection.close();
        return list;
    }
}
