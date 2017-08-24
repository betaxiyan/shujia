/*
 * 文件名：DisTenantService01.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Administrator
 * 修改时间：2017年8月3日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bonc.nerv.tioa.week.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.week.entity.DisTenantEntity;
import com.bonc.nerv.tioa.week.entity.SearchDisTenant;

/**
 * 
 * 已划配租户服务类
 * 
 * @author zhangwen
 * @version 2017年8月2日
 * @see DisTenantService
 * @since
 */
public interface DisTenantService {

    /**
     * 
     * 加载列表数据并查询
     * 
     * @param searchdisTenant 封装的查询类
     * @param start 起始页
     * @param length 每页数据
     * @param draw ""
     * @return ""
     * @see
     */
    String findList(SearchDisTenant searchdisTenant, Integer start, Integer length, String draw);

    /**
     * 导出Excel
     * 
     * @return  list
     * @see
     */
    List<DisTenantEntity> exportFile();

    /**
     * 
     * 将数据填入到处的表格
     * 
     * @param list  租户列表
     * @param request 请求的request对象
     * @param response  响应的response对象
     * @see
     */
//    void getExcel(List<DisTenantEntity> list, HttpServletRequest request,
//                  HttpServletResponse response);

    /**
     * 
     * Description: <br>
     * 将数据导出到Excel中 new
     * @param list List
     * @param request HTTP
     * @param response HTTP
     * @throws IOException  
     * @see
     */
    void getExcelNew(List<DisTenantEntity> list, HttpServletRequest request,
                            HttpServletResponse response) throws IOException;
    
    /**
     * 
     * 新增内容保存
     * 
     * @param distenantEntity 
     * @return 返回JSON字符串
     * @see
     */
    String addUserPost(DisTenantEntity distenantEntity);

    /**
     * 
     * 根据id验证是否可以删除
     * 
     * @param tdId 租户序号
     * @return  返回boolean值
     * @see
     */
    boolean validateById(long tdId);

    /**
     * 
     * 进行删除操作
     * 
     * @param tdId 
     * @see
     */
    void deleteDisTenant(long tdId);

    /**
     * 
     * 加载需要编辑的内容
     * 
     * @param tdId 租户序号
     * @return ""
     * @see
     */
    DisTenantEntity update(long tdId);

    /**
     * 
     * 对编辑的内容进行保存
     * 
     * @param ditenantEntity 已划配租户实体类
     * @return  ""
     * @see
     */
    String updateP(DisTenantEntity ditenantEntity);
    
    /**
     * 
     * 获取中间表数据到tioa_tenant_distribute_show 表中
     * @return  ""
     * @see
     */
    void getMidDataToTtd();

    /**
     * 
     * Description: <br>
     * 请求分析OrcalAndFtp表
     * @param excelFile 
     * @throws IOException 
     * @see
     */
    void analyseOrcalAndFtp(MultipartFile excelFile) throws IOException;
    
    /**
     * 
     * Description: <br>
     * 请求分析Hbase的txt
     * @param txtFile 
     * @throws IOException 
     * @see
     */
    void ananlyseHbase(MultipartFile txtFile) throws IOException;
    
    /**
     * 
     * Description: <br>
     * 请求分析websever的excel
     * @param excelFile 
     * @throws IOException 
     * @see
     */
    void analyseWebSever(MultipartFile excelFile) throws IOException;
    
    /**
     * 
     * Description: <br>
     * 请求分析yarn的excel
     * @param excelFile 
     * @throws IOException 
     * @see
     */
    void analyseYarn(MultipartFile excelFile) throws IOException;
}
