/*
 * 文件名：TenantAroundMgrService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月7日
 */

package com.bonc.nerv.tioa.week.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.week.entity.SearchTenretiredData;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
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
     * 将Excel文件保存到数据库
     * @param file   
     * @throws ParseException 
     * @see
     */
    void saveExcel(MultipartFile file) throws ParseException;
    
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
    
    /**
     * 获取TioaTenantAroundShowEntity所有数据
     * @return list
     * @see
     */
    List<TioaTenantAroundShowEntity> findAllTenantAroundMgr();
    
    /**
     * 修改一条记录后保存到数据库
     * @param tioaTenantAroundShowEntity  
     * @see
     */
    void saveTenantAroundMgr(TioaTenantAroundShowEntity tioaTenantAroundShowEntity);
    
    /**
     * 删除一条记录
     * @param ttaId   
     * @see
     */
    void deleteTenantAroundMgr(Long  ttaId);
    
    
    /**
     * 导出Excel方法
     * @param list 返回集合
     * @param response  
     * @see
     */
    void getExcel(List<TioaTenantAroundShowEntity> list, HttpServletResponse response);
    
    /**
     * Description: 导出Excel
     * @return  list
     * @see
     */
    List<TioaTenantAroundShowEntity> exportTenretired();
}
