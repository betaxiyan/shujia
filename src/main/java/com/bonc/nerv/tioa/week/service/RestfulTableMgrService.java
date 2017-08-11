/*
 * 文件名：RestfulTableMgrService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：HCN
 * 修改时间：2017年8月10日
 */

package com.bonc.nerv.tioa.week.service;

/**
 * 
 * 接口健康信息服务层
 * 封装接口健康信息给前台
 * @author HCN
 * @version 2017年8月10日
 * @see RestfulTableMgrService
 * @since
 */
public interface RestfulTableMgrService {

    /**
     * 返回接口健康信息： 接口名  访问地址  接口状态  检查日期
     * @return String
     * @see
     */
    String restfulHealth();
}
