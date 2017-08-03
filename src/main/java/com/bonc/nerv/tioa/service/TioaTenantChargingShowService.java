/*
 * 文件名：TioaTenantChargingShowService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.service;

import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.entity.TioaTenantChargingShow;

public interface TioaTenantChargingShowService {
    public void save(MultipartFile file) throws ParseException;
    
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
}
