/*
 * 文件名：UpdateDistributeService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：HCN
 * 修改时间：2017年8月22日
 */

package com.bonc.nerv.tioa.week.service;

/**
 * 刷新已化配租户情况表服务类
 * @author HCN
 * @version 2017年8月22日
 * @see UpdateDistributeService
 * @since
 */
public interface UpdateDistributeService {

    /**
     * 刷新已化配租户信息数据
     * @return 是否刷新成功
     * @see
     */
    Boolean getMidDataToTtd();
}
