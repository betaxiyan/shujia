/*
 * 文件名：TenantResourceMidService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月2日
 */

package com.bonc.nerv.tioa.week.service;
/**
 * 
 * 获取我的资源全部数据服务类
 * @author zhiyong
 * @version 2017年8月2日
 * @see TenantResourceService
 * @since
 */
public interface TenantResourceService {
    
    /**
     * 调用接口获取我的资源数据写入数据库
     * @see
     */
    void tenResToDb();
}
