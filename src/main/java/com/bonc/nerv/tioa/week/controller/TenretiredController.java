/*
 * 文件名：TenRetiredController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年7月27日
 */

package com.bonc.nerv.tioa.week.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.nerv.tioa.week.entity.SearchTenretiredData;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
import com.bonc.nerv.tioa.week.service.TenretiredService;

/**
 * 
 * 已退租户Controller
 * @author ymm
 * @version 2017年8月2日
 * @see TenretiredController
 * @since
 */
@Controller
public class TenretiredController {
    
    /**
     * 引入service层
     */
    @Autowired
    private TenretiredService tenretiredService;
    
    /**
     * Description: 退租用户信息列表
     * @return 租户信息列表页面
     * @see
     */
    @RequestMapping(value={"/view"},method=RequestMethod.GET)
    public String index(){
        return "view/data_view";
    }
    
    /**
     * Description: 退租用户信息列表
     * @param searchData 查询对像
     * @param start
     * @param length
     * @param draw
     * @return 查询列表
     * @see
     */
    @RequestMapping(value={"/tenant/findTenretiredList"})
    @ResponseBody
    public String findTenretiredList(SearchTenretiredData searchData,Integer start,Integer length, String draw){
        return  tenretiredService.findTenretiredList(searchData, start, length, draw);
    }
    
    /**
     * Description: 新增退租用户记录
     * @param tenretiredEntity 租户实体对象
     * @return 保存方法
     * @see
     */
    @RequestMapping(value={"/tenant/saveTenretired"})
    @ResponseBody
    public String saveTenretired(TenretiredEntity tenretiredEntity){
        return  tenretiredService.save(tenretiredEntity);
    }
    
    /**
     * 
     * 编辑时根据id回显对象信息
     * @param tlId 表id
     * @return 查询对象
     * @see
     */
    @RequestMapping(value = {"/tenant/tenretired/edit"}, method = RequestMethod.GET)
    @ResponseBody
    public TenretiredEntity findOne(long tlId) {
        TenretiredEntity tenretiredEntity= tenretiredService.findOne(tlId);
        return tenretiredEntity;
    }

    /**
    * 
    * 更新已退租户
    * @param tenretiredEntity 已退租户对象
    * @return 更新状态
    * @see
    */
    @RequestMapping(value = {"/tenant/tenretired/update"}, method = RequestMethod.GET)
    @ResponseBody
    public String update(TenretiredEntity tenretiredEntity) {
        return tenretiredService.update(tenretiredEntity);
    }
    
    /**
     * 根据id验证是否可以删除
     * @param tlId 
     * @return boolean
     * @see
     */
    @RequestMapping(value = {"/tenant/retired/validateByTlId"}, method = RequestMethod.GET)
    @ResponseBody
    public boolean validateByUserId(Long tlId) {
        return tenretiredService.validateByTlId(tlId);
    }
    
    /**
     * 删除已退租户记录
     * @param tlId 
     * @return 重定向到列表页
     * @see
     */
    @RequestMapping(value = {"/tenant/tenretired/delete"}, method = RequestMethod.GET)
    public String delete(long tlId) {
        tenretiredService.delete(tlId);
        return "redirect:/view";
    }
     
    /**
     * 导出已退租户记录
     * @param searchData 查询条件实体类
     * @param response 
     * @see
     */
    @RequestMapping(value={"/tenant/exportTenretired"})
    @ResponseBody
    public void exportTenretired(SearchTenretiredData searchData, HttpServletRequest request,HttpServletResponse response){
        List<TenretiredEntity> list=tenretiredService.exportTenretired(searchData);
        //tenretiredService.getExcel(list, response);
        try {
            tenretiredService.getExcelNew(list,request , response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
