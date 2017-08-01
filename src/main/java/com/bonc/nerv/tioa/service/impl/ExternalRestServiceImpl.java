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
import com.bonc.nerv.tioa.dao.AccountFilecountMidEntityDao;
import com.bonc.nerv.tioa.dao.ResourceAccountMidEntityDao;
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
    
    @Autowired 
    private ResourceAccountMidEntityDao resourceAccountMidEntityDao;
    
    @Autowired
    private AccountFilecountMidEntityDao accountFilecountMidEntityDao;
    
    public void resAccToDb() {
            String jsonString =  WebClientUtil.doGet("http://192.168.50.40:10088/bonc-nerv/findAllAccount",null);
            //String jsonString ="{\"message\":\"ok\",\"data\":{\"accSeqs\":[{\"type\":\"ftp\",\"tenantAccount\":\"opdw1_097hk\",\"tenantName\":\"黑龙江集成-标签\",\"seqName\":\"opdw1_097hk\"},{\"type\":\"ftp\",\"tenantAccount\":\"opdw1_097jc\",\"tenantName\":\"黑龙江集成-房地产\",\"seqName\":\"opdw1_097jc\"}]},\"success\":true}";
            ObjectMapper mapper = new ObjectMapper();
            //将ResourceAccountMidEntity的数据封装成list集合
            JavaType javaType = mapper.getTypeFactory().constructParametricType(  
            List.class, ResourceAccountMidEntity.class);  
            List<ResourceAccountMidEntity> resList = null;
            try
            {
                JsonNode node = mapper.readTree(jsonString);
                //排除调用接口出错的情况
                String isSuccess = node.get("success").toString();
                if(isSuccess.equals("false")){
                    System.out.println("接口访问失败！");
                }else{
                    jsonString = node.get("data").get("accSeqs").toString();
                    resList = mapper.readValue(jsonString, javaType);
                }
              
            }
            catch (Exception e)
            {
                
                e.printStackTrace();
            }
            
            System.out.println(resList);
            
            //加个判断resList是不是null的
            if(resList != null) {
                resourceAccountMidEntityDao.save(resList); 
            }
    }


    public void accFcountToDb(String sysDate) {
        String value = sysDate;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sysDate", value);
        String jsonString = WebClientUtil.doGet("http://192.168.50.40:10088/bonc-nerv/findFileCount?sysDate=" + sysDate, null);
        //String jsonString = "{\"message\":\"ok\",\"data\":{\"fileCounts\":[{\"sysDate\":\"201707011600\",\"dfileNum\":\"3124\",\"type\":\"hbase\",\"seqName\":\"hbase_wwl\",\"fileNum\":\"2053\"},{\"sysDate\":\"201707011600\",\"dfileNum\":\"150\",\"type\":\"hbase\",\"seqName\":\"hbase_036\",\"fileNum\":\"130\"},{\"sysDate\":\"201707011600\",\"dfileNum\":\"59\",\"type\":\"hbase\",\"seqName\":\"hbase_038\",\"fileNum\":\"34\"}]},\"success\":true}";
        ObjectMapper mapper = new ObjectMapper();
        
        //将AccountFilecountMidEntity的数据封装成list集合
        JavaType javaType = mapper.getTypeFactory().constructParametricType(  
            List.class, AccountFilecountMidEntity.class);  
        List<AccountFilecountMidEntity> resList = null;
       
        try
        {
            JsonNode node = mapper.readTree(jsonString);
            //排除调用接口出错的情况
            String isSuccess = node.get("success").toString();
            if(isSuccess.equals("false")){
                System.out.println("接口访问失败！");
        } else {
            jsonString = node.get("data").get("fileCounts").toString();
            resList = mapper.readValue(jsonString, javaType);
        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        System.out.println(resList);
        //加个判断resList是不是null的
        if (resList!=null) {
            accountFilecountMidEntityDao.save(resList);
        }
    }
}
