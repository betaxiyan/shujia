/*
 * 文件名：MergeExcelService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月7日
 */

package com.bonc.nerv.tioa.week.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 导出Excel
 * @author zhiyong
 * @version 2017年8月8日
 * @see MergeExcelService
 * @since
 */
public interface MergeExcelService {
    
    /**
     * 
     *  导出Excel
     *  @param response HttpServletResponse
     * @see
     */
    void getExcel(HttpServletResponse response);
    
    /**
     * 
     * Description: <br>
     * 新全量导出excel
     * @param request
     * @param response 
     * @see
     */
    void getExcelNew(HttpServletRequest request, HttpServletResponse response);
}
