/*
 * 文件名：TenRetiredController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年7月27日
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

import com.bonc.nerv.tioa.entity.SearchTenretiredData;
import com.bonc.nerv.tioa.entity.TenretiredEntity;
import com.bonc.nerv.tioa.service.TenretiredService;


@Controller
public class TenretiredController {
    
    @Autowired
    TenretiredService tenretiredService;
    
    /**
     * Description: 退租用户信息列表
     * @return 
     * @see
     */
    @RequestMapping(value={"/tenant"},method=RequestMethod.GET)
    public String index(){
        return "tenant/tenretired";
    }
    
    /**
     * Description: 退租用户信息列表
     * @param searchData
     * @param start
     * @param length
     * @param draw
     * @return 
     * @see
     */
    @RequestMapping(value={"/tenant/findTenretiredList"})
    @ResponseBody
    public String findTenretiredList(SearchTenretiredData searchData,Integer start,Integer length, String draw){
        return  tenretiredService.findTenretiredList(searchData, start, length, draw);
    }
    
    /**
     * Description: 新增退租用户记录
     * @param tenretiredEntity
     * @return 
     * @see
     */
    @RequestMapping(value={"/tenant/saveTenretired"})
    @ResponseBody
    public String saveTenretired(TenretiredEntity tenretiredEntity){
        return  tenretiredService.save(tenretiredEntity);
    }
    
    /**
     * 根据分类id验证是否可以删除
     * @param cataId 分类id
     * @return boolean
     * @see
     */
    @RequestMapping(value = {"tenant/retired/validateByTlId"}, method = RequestMethod.GET)
    @ResponseBody
    public boolean validateByUserId(String tlId) {
        return tenretiredService.validateByTlId(tlId);
    }
    
    /**
     * 删除类型
     * @param userId 类型id
     * @return 重定向到列表页
     * @see
     */
    @RequestMapping(value = {"tenant/tenretired/delete"}, method = RequestMethod.GET)
    public String delete(String tlId) {
        tenretiredService.delete(Long.parseLong(tlId));
        return "redirect:/tenant";
    }
    /**
     * 导出已退租户记录
     * @param searchData
     * @param request
     * @param response 
     * @see
     */
    @RequestMapping(value={"/tenant/exportTenretired"})
    @ResponseBody
    public void exportTenretired(SearchTenretiredData searchData,HttpServletResponse response){
        List<TenretiredEntity> list=tenretiredService.exportTenretired(searchData);
        tenretiredService.getExcel(list, response);
    }
}
