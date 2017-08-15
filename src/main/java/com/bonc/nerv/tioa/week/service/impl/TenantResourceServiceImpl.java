/*
 * 文件名：TenantResourceMidServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月2日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.TenantResourceMidDao;
import com.bonc.nerv.tioa.week.entity.TenantResourceMidEntity;
import com.bonc.nerv.tioa.week.service.TenantResourceService;
import com.bonc.nerv.tioa.week.util.WebClientUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 获取我的资源全部数据服务类
 * @author zhiyong
 * @version 2017年8月2日
 * @see TenantResourceServiceImpl
 * @since
 */
@Service("tenantResourceService")
public class TenantResourceServiceImpl implements TenantResourceService{
    
    /**
     * TenantResourceMidDao
     */
    @Autowired
    private TenantResourceMidDao tenantResourceMidDao;
    
    /**
     * 资源管理的有效资源url
     */
    @Value("${resreq.myresource.valid}")
    private String resreqValid;
    
    /**
     * 资源管理无效资源接口url
     */
    @Value("${resreq.myresource.invalid}")
    private String resreqInvalid;
    /**
     * 调用接口获取我的资源数据写入数据库
     * @see
     */
    public void tenResToDb() {
        String validJson =  WebClientUtil.doGet(resreqValid, null);
        String invalidJson =  WebClientUtil.doGet(resreqInvalid, null);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        //将ResourceAccountMidEntity的数据封装成list集合
        List<TenantResourceMidEntity> resultList =new ArrayList<TenantResourceMidEntity>();
        TenantResourceMidEntity entity = null;
        try {
            JsonNode validnode = mapper.readTree(validJson);
            JsonNode invalidnode = mapper.readTree(invalidJson);
            //获取资源有效数据
            for (JsonNode nodeOne : validnode) {
                for (JsonNode nodeTwo : nodeOne) {
                    entity = new TenantResourceMidEntity();
                    entity.setIsInvalid("valid");//是否有效
                    entity.setTenantId(nodeTwo.get("fixResource").get("tenant_id").asText());//租户id
                    entity.setTenantName(nodeTwo.get("fixResource").get("tenant_name").asText());//租户名
                    entity.setServiceType(nodeTwo.get("fixResource").get("request_type").asText());//服务类型
                    String endDate = nodeTwo.get("fixResource").get("update_time").asText();
                    try {
                        Date enDate = format.parse(endDate);
                        entity.setEndRentData(enDate);//更新时间
                    } catch (Exception e) {
                    }
                    Date enDate = format.parse(endDate);
                    entity.setEndRentData(enDate);//更新时间
                    if(nodeTwo.get("expendCodeResource").has("Account_Name")) {
                        entity.setTenantAccount(nodeTwo.get("expendCodeResource").get("Account_Name").asText());//租户账号
                    }
                    if(nodeTwo.get("expendCodeResource").has("IP")) {
                        entity.setIpAddr(nodeTwo.get("expendCodeResource").get("IP").asText());//IP地址
                    }
                    entity.setTypeName(nodeTwo.get("fixResource").get("type_name").asText());//资源类型
                    if(nodeTwo.get("expendCodeResource").has("Storage")) {
                        entity.setStorage(nodeTwo.get("expendCodeResource").get("Storage").asText());//存储使用量
                    } 
                    if(nodeTwo.get("expendCodeResource").has("CPU")) {
                        entity.setCpuNum(nodeTwo.get("expendCodeResource").get("CPU").asText());//CPU 核数
                    }
                    if(nodeTwo.get("expendCodeResource").has("Memory")) {
                        entity.setMemory(nodeTwo.get("expendCodeResource").get("Memory").asText());//内存大小
                    }
                    if(nodeTwo.get("expendCodeResource").has("Service_Name")) {
                        entity.setServiceName(nodeTwo.get("expendCodeResource").get("Service_Name").asText());//服务名
                    }
                    if(nodeTwo.get("expendCodeResource").has("Machine_Room")) {
                        entity.setComputeRoom(nodeTwo.get("expendCodeResource").get("Machine_Room").asText());//机房
                    }
                    if(nodeTwo.get("expendCodeResource").has("Apply_Time")) {
                        String asKDate = nodeTwo.get("expendCodeResource").get("Apply_Time").asText();
                        try {
                            Date askDate = format.parse(asKDate);
                            entity.setAskDate(askDate);//申请日期
                        } catch (Exception e) {
                        }
                    }
                    if(nodeTwo.get("expendCodeResource").has("Start_Date")) {
                        String openDate = nodeTwo.get("expendCodeResource").get("Start_Date").asText();
                        
                        try {
                            Date opeNDate = format.parse(openDate);
                            entity.setOpenDate(opeNDate);//开放日期
                        } catch (Exception e) {
                        }
                    }
                    resultList.add(entity);
                }
            }
            
            //获取资源无效数据
            for (JsonNode nodeOne : invalidnode) {
                for (JsonNode nodeTwo : nodeOne) {
                    entity = new TenantResourceMidEntity();
                    entity.setIsInvalid("invalid");//是否有效
                    entity.setTenantId(nodeTwo.get("fixResource").get("tenant_id").asText());//租户id
                    entity.setTenantName(nodeTwo.get("fixResource").get("tenant_name").asText());//租户名
                    entity.setServiceType(nodeTwo.get("fixResource").get("request_type").asText());//服务类型
                    String endDate = nodeTwo.get("fixResource").get("update_time").asText();
                    try {
                        Date enDate = format.parse(endDate);
                        entity.setEndRentData(enDate);//更新时间
                    } catch (Exception e) {
                    }
                    Date enDate = format.parse(endDate);
                    entity.setEndRentData(enDate);//更新时间
                    if(nodeTwo.get("expendCodeResource").has("Account_Name")) {
                        entity.setTenantAccount(nodeTwo.get("expendCodeResource").get("Account_Name").asText());//租户账号
                    }
                    if(nodeTwo.get("expendCodeResource").has("IP")) {
                        entity.setIpAddr(nodeTwo.get("expendCodeResource").get("IP").asText());//IP地址
                    }
                    entity.setTypeName(nodeTwo.get("fixResource").get("type_name").asText());//资源类型
                    if(nodeTwo.get("expendCodeResource").has("Storage")) {
                        entity.setStorage(nodeTwo.get("expendCodeResource").get("Storage").asText());//存储使用量
                    } 
                    if(nodeTwo.get("expendCodeResource").has("CPU")) {
                        entity.setCpuNum(nodeTwo.get("expendCodeResource").get("CPU").asText());//CPU 核数
                    }
                    if(nodeTwo.get("expendCodeResource").has("Memory")) {
                        entity.setMemory(nodeTwo.get("expendCodeResource").get("Memory").asText());//内存大小
                    }
                    if(nodeTwo.get("expendCodeResource").has("Service_Name")) {
                        entity.setServiceName(nodeTwo.get("expendCodeResource").get("Service_Name").asText());//服务名
                    }
                    if(nodeTwo.get("expendCodeResource").has("Machine_Room")) {
                        entity.setComputeRoom(nodeTwo.get("expendCodeResource").get("Machine_Room").asText());//机房
                    }
                    if(nodeTwo.get("expendCodeResource").has("Apply_Time")) {
                        String asKDate = nodeTwo.get("expendCodeResource").get("Apply_Time").asText();
                        try {
                            Date askDate = format.parse(asKDate);
                            entity.setAskDate(askDate);//申请日期
                        } catch (Exception e) {
                        }
                    }
                    if(nodeTwo.get("expendCodeResource").has("Start_Date")) {
                        String openDate = nodeTwo.get("expendCodeResource").get("Start_Date").asText();
                        
                        try {
                            Date opeNDate = format.parse(openDate);
                            entity.setOpenDate(opeNDate);//开放日期
                        } catch (Exception e) {
                        }
                    }
                    resultList.add(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(resultList!=null) {
            System.out.println(resultList);
            tenantResourceMidDao.deleteAll();//清空数据库
            tenantResourceMidDao.save(resultList);//刷新数据库
        }
        
    }
}
