/*
 * 文件名：MainController.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：zhangwen 修改时间：2017年7月27日 跟踪单号：
 * 修改单号： 修改内容：
 */

package com.bonc.nerv.tioa.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.nerv.tioa.entity.DisTenantEntity;
import com.bonc.nerv.tioa.entity.SearchDisTenant;
import com.bonc.nerv.tioa.service.DisTenantService;

/**
 * 
 * 已划配租户控制类
 * 
 * @author zhangwen
 * @version 2017年8月2日
 * @see DisTenantController
 * @since
 */
@Controller
public class DisTenantController {

    /*
     * distenantService
     */
    @Autowired
    private DisTenantService distenantService;
    
    /**
     * 展示列表信息
     * 
     * @return 返回页面
     * @see
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers() {
        return "view/data_view";
    }

    /**
     * 
     * 加载列表数据
     * 进行查询
     * 
     * @param searchdisTenant 封装查询类
     * @param start 开始页数
     * @param length 每页条数
     * @param draw ""
     * @return  ""
     * @see
     */
    @RequestMapping(value = "/users/findlist", method = RequestMethod.GET)
    @ResponseBody
    public String findList(SearchDisTenant searchdisTenant,Integer start, Integer length, String draw) {
        return distenantService.findList(searchdisTenant, start, length, draw);
    }

    /**
     * 导出列表信息
     * 
     * @param request 请求的request对象
     * @param response 响应的response对象
     * @throws Exception 抛出异常
     * @see
     */
    @RequestMapping(value = "/users/export")
    public void exportFile(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        List<DisTenantEntity> list = distenantService.exportFile();
        distenantService.getExcel(list, request, response);

    }

    /**
     * 添加列表信息
     * 
     * @param distenantEntity  已划配租户实体
     * @return ""
     * @see
     */
    @RequestMapping(value = "users/addP", method = RequestMethod.GET)
    @ResponseBody
    public String addUserPost(DisTenantEntity distenantEntity) {
        return distenantService.addUserPost(distenantEntity);
    }
    
   /**
    * 
    * 根据id验证是否可以删除
    * 
    * @param tdId 租户序号
    * @return ""
    * @see
    */
    @RequestMapping(value="users/validateById",method=RequestMethod.GET)
    @ResponseBody
    public boolean validateById(long tdId){
        return distenantService.validateById(tdId);
    }
    
    /**
     * 
     * 进行删除操作
     * 
     * @param tdId
     * @return 重定向到方法
     * @see
     */
    @RequestMapping(value = "users/delete" ,method = RequestMethod.GET)
    public String deleteDisTenant(long tdId){
        distenantService.deleteDisTenant(tdId);
        return "redirect:/users";
        
    }
    
    /**
     * 
     * 进行编辑操作，加载编辑内容
     * 
     * @param tdId 序号
     * @return  distenantEntity
     * @see
     */
    @RequestMapping(value = "users/update",method = RequestMethod.GET)
    @ResponseBody
    public DisTenantEntity update(long tdId){
        DisTenantEntity distenantEntity = distenantService.update(tdId);
        return distenantEntity;
    }
    
    /**
     * 
     * 编辑内容并保存
     * 
     * @param ditenantEntity 
     * @return " "
     * @see
     */
    @RequestMapping(value ="users/updateP",method = RequestMethod.GET)
    @ResponseBody
    public String updateP(DisTenantEntity ditenantEntity){
        return distenantService.updateP(ditenantEntity);
    }
}
