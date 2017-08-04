/*
 * 文件名：TioaTenantChargingShowService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.service;

import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.entity.TioaTenantChargingShow;
/**
 * 
 * @author Jingege
 * @version 2017年8月3日
 * @see TioaTenantChargingShowService
 * @since
 */
public interface TioTenChaShoService {
    /**
     * 将Excel文件保存到数据库
     * @param file   
     * @throws ParseException 
     * @see
     */
    void saveExcel(MultipartFile file) throws ParseException;
    
    /**
     * Description: 保存新增数据
     * 
     * @param tioaTenantChargingShow 
     * @see 
     */
    void save(TioaTenantChargingShow tioaTenantChargingShow);
    
    /**
     * Description: 根据id删除表记录
     * @param id 
     * @see 
     */
    void deleteById(Long id);
    
    /**
     * Description: 将数据保存到excel文件
     * @see 
     */
    void savaToFile();
}
