/*
 * 文件名：TenantAroundMgrService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月7日
 */

package com.bonc.nerv.tioa.week.service;

import java.util.List;

import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;

/**
 * 租户周边信息管理服务层
 * @author leijin
 * @version 2017年8月7日
 * @see TenantAroundMgrService
 * @since
 */
public interface TenantAroundMgrService {
    /**
     * 
     */
    /**
     * 从http://coptest.bonc.yz/portal/pure/tenant/list 获取id和name
     * @param http 
     * @return id and name
     * @see
     */
    void saveIdAndNameFromHttp();
    
    /**
     * 从数据库导出到Excel进行批量修改
     * @see
     */
    void exportFromTenantAroundMgr();
    
    /**
     * 将批量修改的Excel导入到数据库
     * @see
     */
    void importToTenantAroundMgr();
}
