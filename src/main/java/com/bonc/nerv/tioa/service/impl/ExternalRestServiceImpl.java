/*
 * 文件名：ExternalRestServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年7月31日
 */

package com.bonc.nerv.tioa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bonc.nerv.tioa.dao.AccountFilecountMidDao;
import com.bonc.nerv.tioa.dao.ResourceAccountMidDao;
import com.bonc.nerv.tioa.entity.AccountFilecountMidEntity;
import com.bonc.nerv.tioa.entity.ResourceAccountMidEntity;
import com.bonc.nerv.tioa.service.ExternalRestService;
import com.bonc.nerv.tioa.util.WebClientUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 调用外部restful接口的服务类
 * @author yuanpeng
 * @version 2017年7月31日
 * @see ExternalRestServiceImpl
 * @since
 */
@Service("externalRestService")
public class ExternalRestServiceImpl implements ExternalRestService {
    
    /**
     * ResourceAccountMidDao
     */
    @Autowired
    private ResourceAccountMidDao resourceAccountMidDao;
    
    /**
     * AccountFilecountMidDao
     */
    @Autowired
    private AccountFilecountMidDao accountFilecountMidDao;
    
    /**
     * 调用接口获取资源和账号数据写入数据库中
     * @see
     */
    public void resAccToDb() {
        String jsonString =  WebClientUtil.doGet("http://192.168.50.40:10088/bonc-nerv/findAllAccount",null);
        ObjectMapper mapper = new ObjectMapper();
        //将ResourceAccountMidEntity的数据封装成list集合
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, ResourceAccountMidEntity.class);  
        List<ResourceAccountMidEntity> resList = null;
        try{
            JsonNode node = mapper.readTree(jsonString);
            //排除调用接口出错的情况
            String isSuccess = node.get("success").toString();
            if(isSuccess.equals("false")){
                System.out.println("接口访问失败！");
            }else{
                jsonString = node.get("data").get("accSeqs").toString();
                resList = mapper.readValue(jsonString, javaType);
            }
          
        }catch (Exception e){
            
            e.printStackTrace();
        }
        
        System.out.println(resList);
        
        //加个判断resList是不是null的
        if(resList != null) {
            resourceAccountMidDao.save(resList); 
        }
    }

    /**
     * 调用接口获取账号和文件数数据写入数据库中
     * @param sysDate 时间参数
     * @see
     */
    public void accFcountToDb(String sysDate) {
        String value = sysDate;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sysDate", value);
        String jsonString = WebClientUtil.doGet("http://192.168.50.40:10088/bonc-nerv/findFileCount?sysDate=" + sysDate, null);
        ObjectMapper mapper = new ObjectMapper();
        
        //将AccountFilecountMidEntity的数据封装成list集合
        JavaType javaType = mapper.getTypeFactory().constructParametricType(  
            List.class, AccountFilecountMidEntity.class);  
        List<AccountFilecountMidEntity> resList = null;
       
        try {
            JsonNode node = mapper.readTree(jsonString);
            //排除调用接口出错的情况
            String isSuccess = node.get("success").toString();
            if(isSuccess.equals("false")){
                System.out.println("接口访问失败！");
            } else {
                jsonString = node.get("data").get("fileCounts").toString();
                resList = mapper.readValue(jsonString, javaType);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    
        System.out.println(resList);
        //加个判断resList是不是null的
        if (resList!=null) {
            accountFilecountMidDao.save(resList);
        }
    }
}
