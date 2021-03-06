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
     * Description: <br>
     * 新全量导出excel
     * @param request HTTP
     * @param response HTTP
     * @return 判断是否成功
     * @see
     */
    Boolean getExcelNew(HttpServletRequest request, HttpServletResponse response);
}
