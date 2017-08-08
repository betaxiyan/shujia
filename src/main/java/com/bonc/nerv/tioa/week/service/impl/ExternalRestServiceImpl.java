/*
 * 文件名：ExternalRestServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年7月31日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.AccountFilecountMidDao;
import com.bonc.nerv.tioa.week.dao.ResourceAccountMidDao;
import com.bonc.nerv.tioa.week.entity.AccountFilecountMidEntity;
import com.bonc.nerv.tioa.week.entity.ResourceAccountMidEntity;
import com.bonc.nerv.tioa.week.service.ExternalRestService;
import com.bonc.nerv.tioa.week.util.WebClientUtil;
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
     * 
     */
    @Value("${bonc.restful.findfilecount}")
    private String findFileCount;
    
    /**
     * 
     */
    @Value("${bonc.restful.findAllAccount}")
    private String findAllAccount;
    
    /**
     * 调用接口获取资源和账号数据写入数据库中
     * @see
     */
    public void resAccToDb() {
        
        List<ResourceAccountMidEntity> resList = resAccRest();
        
        if(resList == null || resList.size() == 0){
            System.out.println("List<ResourceAccountMidEntity>为空");
        } else {
            resourceAccountMidDao.save(resList);
        }
    }

    /**
     * 
     * Description: <br>
     * 从findAllAccount接口获取数据
     * @return List<ResourceAccountMidEntity>
     * @see
     */
    public List<ResourceAccountMidEntity> resAccRest(){
        String jsonString =  WebClientUtil.doGet(findAllAccount,null);
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
        return resList;
    }
    
    /**
     * 调用接口获取账号和文件数数据写入数据库中
     * @param sysDate 时间参数
     * @see
     */
    public void accFcountToDb(String sysDate) {
        List<AccountFilecountMidEntity> resList = accFcountRest(sysDate);
        if(resList == null || resList.size() == 0){
            System.out.println("List<AccountFilecountMidEntity>为空");
        } else {
            accountFilecountMidDao.save(resList);
        }
    }

    /**
     * 
     * Description: <br>
     * 从findAllAccount接口获取数据
     * @param sysDate  
     * @return List<AccountFilecountMidEntity>
     * @see
     */
    public List<AccountFilecountMidEntity> accFcountRest(String sysDate){
        String value = sysDate;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sysDate", value);
        String jsonString = WebClientUtil.doGet(findFileCount+"sysDate=" + sysDate, null);
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
        return resList;
    }
}