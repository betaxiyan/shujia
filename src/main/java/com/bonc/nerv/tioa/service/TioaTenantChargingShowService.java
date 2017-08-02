/*
 * 文件名：TioaTenantChargingShowService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.entity.TioaTenantChargingShow;

public interface TioaTenantChargingShowService {
    public void save(MultipartFile file) throws ParseException;
}
