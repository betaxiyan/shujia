/*
 * 文件名：MainController.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：zhangwen 修改时间：2017年7月27日 跟踪单号：
 * 修改单号： 修改内容：
 */

package com.bonc.nerv.tioa.week.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.bonc.nerv.tioa.week.entity.DisTenantEntity;
import com.bonc.nerv.tioa.week.entity.SearchDisTenant;
import com.bonc.nerv.tioa.week.service.DisTenantService;

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
    @RequestMapping(value = "/users/disExport")
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
    
    /**
     * 
     * Description: <br>
     * 请求分析OrcalAndFtp表
     * @param excelFile 
     * @see
     */
    @RequestMapping(value ="analyse/orcftp",method = RequestMethod.POST)
    @ResponseBody
    public String analyseOrcalAndFtp(@RequestParam(value="upload")MultipartFile excelFile)throws ParseException{
        System.out.println(excelFile);
        try {
            distenantService.analyseOrcalAndFtp(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("出现错误");
        }
        return JSON.toJSONString("导入成功");
    }
    
    /**
     * 
     * Description: <br>
     * 请求分析Hbase的txt
     * @param txtFile 
     * @see
     */
    @RequestMapping(value ="analyse/hbase",method = RequestMethod.POST)
    @ResponseBody
    public String ananlyseHbase(@RequestParam(value="upload")MultipartFile txtFile) throws ParseException{
        try {
            distenantService.ananlyseHbase(txtFile);
        }catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("出现错误");
        }
        return JSON.toJSONString("导入成功");
    }
    
    /**
     * 
     * Description: <br>
     * 请求分析websever的excel
     * @param excelFile 
     * @see
     */
    @RequestMapping(value ="analyse/websever",method = RequestMethod.POST)
    @ResponseBody
    public String analyseWebSever(@RequestParam(value="upload")MultipartFile excelFile) throws ParseException{
        try {
            distenantService.analyseWebSever(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("出现错误");
        }
        return JSON.toJSONString("导入成功");
    }
    
    /**
     * 
     * Description: <br>
     * 请求分析yarn的excel
     * @param excelFile 
     * @see
     */
    @RequestMapping(value ="analyse/yarn",method = RequestMethod.POST)
    @ResponseBody
    public String analyseYarn(@RequestParam(value="upload")MultipartFile excelFile) throws ParseException{
        try {
            distenantService.analyseYarn(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("出现错误");
        }
        return JSON.toJSONString("导入成功");
    }
}
