/*
 * 文件名：TioaTenantChargingShowService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.week.service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.week.entity.TioaTenantChargingShow;
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
     * @param request 请求的request对象
     * @param response  响应的response对象
     * @see 
     */
    void savaToFile(HttpServletRequest request,
                    HttpServletResponse response);
    
    /**
     * Description: 删除全部表记录
     * @see 
     */
    void deleteAll();
    
    /**
     * 
     * Description: <br>
     * 向接口人发送邮件
     * @see
     */
    void sendEmailToInterface();
}
