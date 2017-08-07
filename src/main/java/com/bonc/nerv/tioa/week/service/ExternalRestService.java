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
    
    
    List<AccountFilecountMidEntity> accFcountRest(String sysDate);
    
    List<ResourceAccountMidEntity> resAccRest();
    
    
}
