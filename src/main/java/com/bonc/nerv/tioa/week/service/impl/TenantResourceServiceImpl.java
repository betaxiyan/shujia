/*
 * 文件名：TenantResourceMidServiceImpl.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：zhiyong
 * 修改时间：2017年8月2日
 */

package com.bonc.nerv.tioa.week.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
 * 获取我的资源全部数据服务类
 * 
 * @author zhiyong
 * @version 2017年8月2日
 * @see TenantResourceServiceImpl
 * @since
 */
@Service("tenantResourceService")
public class TenantResourceServiceImpl implements TenantResourceService {

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
     * 
     * @see
     */
    public void tenResToDb() {
        String validJson =  WebClientUtil.doGet(resreqValid, null);
        String invalidJson =  WebClientUtil.doGet(resreqInvalid, null);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        //将ResourceAccountMidEntity的数据封装成list集合
        List<TenantResourceMidEntity> resultList =new ArrayList<TenantResourceMidEntity>();
        try {
            JsonNode validnode = mapper.readTree(validJson);
            JsonNode invalidnode = mapper.readTree(invalidJson);
            
            
            analyseResreqJson(resultList, validnode, format, "valid");
            analyseResreqJson(resultList, invalidnode, format, "invalid");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(resultList!=null) {
            //System.out.println(resultList);
            //tenantResourceMidDao.deleteAll();//清空数据库
            tenantResourceMidDao.save(resultList);//刷新数据库
        }

    }

    /**
     * 将带单位的字符串换算成以GB为单位的数值字符串
     * 
     * @param source
     *            带单位的字符串
     * @return String
     * @see
     */
    public String storageToGB(String source) {
        if (source.contains("T")) {
            String[] string = source.split("T");
            if (string[0].trim().equals("--")) {
                source = string[0].trim();
            }else if (string[0].trim().length()==0) {
                source = string[0].trim();
            }else{
                source = Double.parseDouble(string[0].trim()) * 1024 + "";
            }
        } else if (source.contains("G")) {
            String[] string = source.split("G");
            source = string[0].trim();
        } else if (source.contains("M")) {
            String[] string = source.split("M");
            if (string[0].trim().equals("--")) {
                source = string[0].trim();
            }else if (string[0].trim().length()==0) {
                source = string[0].trim();
            }else{
                source = Double.parseDouble(string[0].trim()) / 1024 + "";
            }
        } else if (source.contains("K")) {
            String[] string = source.split("K");
            if (string[0].trim().equals("--")) {
                source = string[0].trim();
            }else if (string[0].trim().length()==0) {
                source = string[0].trim();
            }else{
                source = Double.parseDouble(string[0].trim()) / 1024 / 1024 + "";
            }
        } else {
            // 没单位默认为GB，去除首尾多余空格
            source = source.trim();
        }
        return source;
    }
    
    /**
     * 简单工具类，将带单位的cpu核数字符串转换为不带单位的cpu核数
     * @param cpuNum cpu核数
     * @return String
     * @see
     */
    public String cpuNumNoUnit(String cpuNum){
        if (cpuNum.contains("核")) {
            String[] string = cpuNum.split("核");
            cpuNum = string[0].trim();
        }
        return cpuNum;
    }
    
    /**
     * 简单工具类，获取一个数字组成的随机字符串
     * @return String
     * @see
     */
    public String getRandomId(){
        Random ran = new Random();
        int a=ran.nextInt(99999999);
        int b=ran.nextInt(99999999);
        long l=a*125237L+b;
        String num=String.valueOf(l);
        return num;
    }
    
    /**
     * 
     * Description: <br>
     * 分析租户资源的json数据
     * @param resultList 结果集
     * @param node JsonNode
     * @param format 
     * @param isvalid 是否是有效资源
     * @see
     */
    private void analyseResreqJson(List<TenantResourceMidEntity> resultList, JsonNode node, SimpleDateFormat format, String isvalid){
        for (JsonNode nodeOne : node) {
            for (JsonNode nodeTwo : nodeOne) {
                TenantResourceMidEntity entity = new TenantResourceMidEntity();
                entity.setIsInvalid(isvalid);//是否有效 invalid
                entity.setRresId(nodeTwo.get("fixResource").get("rres_id").asText()); //资源id
                entity.setTenantId(nodeTwo.get("fixResource").get("tenant_id").asText());//租户id
                entity.setTenantName(nodeTwo.get("fixResource").get("tenant_name").asText());//租户名
                if(nodeTwo.get("fixResource").has("tenantCharacter")){
                    entity.setServiceType(nodeTwo.get("fixResource").get("tenantCharacter").asText());//服务类型
                }
                //entity.setServiceType(nodeTwo.get("fixResource").get("request_type").asText());//服务类型
                String endDate = nodeTwo.get("fixResource").get("update_time").asText();
                try {
                    Date enDate = format.parse(endDate);
                    entity.setEndRentDate(enDate);//更新时间
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(nodeTwo.get("expendCodeResource").has("Account_Name")) {
                    entity.setTenantAccount(nodeTwo.get("expendCodeResource").get("Account_Name").asText());//租户账号
                }
                if(nodeTwo.get("expendCodeResource").has("IP")) {
                    entity.setIpAddr(nodeTwo.get("expendCodeResource").get("IP").asText());//IP地址
                }
                entity.setTypeName(nodeTwo.get("fixResource").get("type_name").asText());//资源类型
                if(nodeTwo.get("expendCodeResource").has("Storage")) {
                    // 将单位变成默认的GB
                    String storage = nodeTwo.get("expendCodeResource").get("Storage").asText();
                    storage = storageToGB(storage);
                    entity.setStorage(storage);// 存储总量
                } 
                if(nodeTwo.get("expendCodeResource").has("CPU")) {
                    String cpuNum = nodeTwo.get("expendCodeResource").get("CPU").asText();
                    cpuNum = cpuNumNoUnit(cpuNum);
                    if (nodeTwo.get("expendCodeResource").has("CPU_Max")) {
                        String cpuMax = nodeTwo.get("expendCodeResource").get("CPU_Max").asText();
                        cpuMax = cpuNumNoUnit(cpuMax);
                        cpuNum = cpuNum+"-"+cpuMax;
                    }
                    entity.setCpuNum(cpuNum);// CPU核数
                }
                if(nodeTwo.get("expendCodeResource").has("Memory")) {
                    // 将单位变成默认的GB
                    String memory = nodeTwo.get("expendCodeResource").get("Memory").asText();
                    memory = storageToGB(memory);
                    entity.setMemory(memory);// 内存大小
                }
                if(nodeTwo.get("expendCodeResource").has("Service_Name")) {
                    entity.setServiceName(nodeTwo.get("expendCodeResource").get("Service_Name").asText());//服务名
                }
                if(nodeTwo.get("expendCodeResource").has("Path")) {
                    entity.setPath(nodeTwo.get("expendCodeResource").get("Path").asText());//目录路径
                }
                if(nodeTwo.get("expendCodeResource").has("Queue_Name")) {
                    entity.setSequenceName(nodeTwo.get("expendCodeResource").get("Queue_Name").asText());//队列名
                }
                if(nodeTwo.get("expendCodeResource").has("Count")) {
                    entity.setCount(nodeTwo.get("expendCodeResource").get("Count").asText());//计算资源
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
                //添加recordId
                String recordId = getRandomId();
                entity.setRecordId(recordId);
                resultList.add(entity);
            }
        }
    }
    
}
