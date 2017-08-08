/*
 * 文件名：ExcelExportController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.week.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
 * Excel及邮箱控制类
 * @author zhiyong
 * @version 2017年8月8日
 * @see ExcelEmailMgrController
 * @since
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.nerv.tioa.week.service.MergeExcelService;
/**
 * 
 * 导出Excel
 * @author zhiyong
 * @version 2017年8月8日
 * @see ExcelEmailMgrController
 * @since
 */
@Controller
public class ExcelEmailMgrController {
    
    /**
     * MergeExcelService
     */
    @Autowired
    private MergeExcelService mergeExcelService;
    
    /**
     * 导出Excel
     * @param response HttpServletResponse
     * @return 操作提示信息
     * @see
     */
    @RequestMapping("/getExcel")
    @ResponseBody
    public String getExcel(HttpServletResponse response) {
        mergeExcelService.getExcel(response);
        return "导出Excel成功！";
    }
}
