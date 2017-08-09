/*
 * 文件名：ExternalRestService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年7月31日
 */

package com.bonc.nerv.tioa.week.service;

import java.util.List;

import com.bonc.nerv.tioa.week.entity.AccountFilecountMidEntity;
import com.bonc.nerv.tioa.week.entity.ResourceAccountMidEntity;

/**
 * 
 * 调用外部restful接口的服务类
 * @author yuanpeng
 * @version 2017年7月31日
 * @see ExternalRestService
 * @since
 */
public interface ExternalRestService {

    /**
     * 
     * Description: <br>
     * 调用接口获取资源和账号数据写入数据库中
     * @see
     */
    void resAccToDb();
    
    /**
     * 
     * Description: <br>
     * 调用接口获取账号和文件数数据写入数据库中
     * @param sysDate 时间参数
     * @see
     */
    void accFcountToDb(String sysDate);
    
    /**
     * 
     * Description: <br>
     * 通过日期查询文件统计
     * @param sysDate 
     * @return   List<AccountFilecountMidEntity>
     * @see
     */
    List<AccountFilecountMidEntity> accFcountRest(String sysDate);
    
    /**
     * 
     * Description: <br>
     * 查询租户信息
     * @return  List<ResourceAccountMidEntity>
     * @see
     */
    List<ResourceAccountMidEntity> resAccRest();
    
    
}
