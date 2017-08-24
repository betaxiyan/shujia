/*
 * 文件名：UpdateDistributeServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：HCN
 * 修改时间：2017年8月22日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.DisTenantDao;
import com.bonc.nerv.tioa.week.entity.DisTenantEntity;
import com.bonc.nerv.tioa.week.service.ExternalRestService;
import com.bonc.nerv.tioa.week.service.UpdateDistributeService;

/**
 * 刷新已化配租户情况服务层的实现类
 * @author HCN
 * @version 2017年8月22日
 * @see UpdateDistributeServiceImpl
 * @since
 */
@Service("updateDistributeService")
public class UpdateDistributeServiceImpl implements UpdateDistributeService{
    
    /**
     * DisTenantDao
     */
    @Autowired
    private DisTenantDao disTenantDao;
    /**
     * externalRestService
     */
    @Autowired
    private ExternalRestService externalRestService;
    
    /**
     * 获取中间表数据到tioa_tenant_distribute_show 表中
     */
    @SuppressWarnings("deprecation")
    @Override
    public Boolean getMidDataToTtd() {
        Boolean result = false;
        //组合资源表为基础
        List<DisTenantEntity> disTenantEntities = disTenantDao.basicOfDisTenant();
        //租户资源表和租户周边信息表数据整合
        List<DisTenantEntity> trmAndTta = disTenantDao.fixPartOfDisTenant();
        //租户资源表和cpu_memory_mid表的数据整合
        List<DisTenantEntity> trmAndCmm = disTenantDao.fixTrmAndCmm();
        //租户资源表和resource_usage_mid_entity表的数据整合
        List<DisTenantEntity> trmAndRum = fixTrmAndRum();
        /* 文件数获取  
          1.先按时间查询数据到表account_filecount_mid中,时间为往前最近一次 00或者30的数据
          2.租户资源表和account_filecount_mid联合查询，得到含有文件数的DisTenantEntity列表
        */
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        int minute = now.getMinutes();
        if ( minute>= 0 && minute <30) {
            now.setMinutes(0);
        }else{
            now.setMinutes(30);
        }
        String sysDate = format.format(now);
        externalRestService.accFcountToDb(sysDate);
        //租户资源表和account_filecount_mid联合查询
        List<DisTenantEntity> trmAndAfm = disTenantDao.fixTrmAndAfm();
        
        //合并数据   recordId为合并依据
        for(DisTenantEntity disTenantEntity : disTenantEntities){
            String recordId = disTenantEntity.getRecordId();
           // String resourceType = disTenantEntity.getResourceType();
            for(DisTenantEntity disTenantEntity1:trmAndTta){
                String recordId1 = disTenantEntity1.getRecordId();
                if (recordId.equals(recordId1)) {
                    //在租户周边表tta中取三个数据
                    disTenantEntity.setTenantBoss(disTenantEntity1.getTenantBoss());
                    disTenantEntity.setTenantTel(disTenantEntity1.getTenantTel());
                    disTenantEntity.setTenantLevel(disTenantEntity1.getTenantLevel());
                }
            }
            for(DisTenantEntity disTenantEntity2 : trmAndCmm){
                String recordId2 = disTenantEntity2.getRecordId();
                if (recordId.equals(recordId2)) {
                    //在cpu内存中间表cmm中取四个数据
                    disTenantEntity.setCpuMax(disTenantEntity2.getCpuMax());
                    disTenantEntity.setCpuAvg(disTenantEntity2.getCpuAvg());
                    disTenantEntity.setMemoryAvg(disTenantEntity2.getMemoryAvg());
                    disTenantEntity.setMemoryMax(disTenantEntity2.getMemoryMax());
                }
            }
            for(DisTenantEntity disTenantEntity3 : trmAndRum){
                String recordId3 = disTenantEntity3.getRecordId();
                if (recordId.equals(recordId3)) {
                    //在存储使用大小表rum中取一个数据
                    disTenantEntity.setStorageUsage(disTenantEntity3.getStorageUsage());
                }
            }
            for(DisTenantEntity disTenantEntity4 : trmAndAfm){
                String recordId4 = disTenantEntity4.getRecordId();
                if (recordId.equals(recordId4)) {
                    //在文件数表afm中获取一个数据
                    disTenantEntity.setFileCount(disTenantEntity4.getFileCount());
                }
            }
            //计算存储使用占比
            if(disTenantEntity.getStorageUsage() != null && disTenantEntity.getStorage() != null){
                Double storage = Double.parseDouble(disTenantEntity.getStorage());
                Double storageUsage = Double.parseDouble(disTenantEntity.getStorageUsage());
                disTenantEntity.setStorageUsageRate(storageUsage/storage);
            }
        }
        if (disTenantEntities != null) {
            disTenantDao.deleteAll();
            disTenantDao.save(disTenantEntities);
            result = true;  //合并成功
        }
        return result;
    }
    
    /**
     * 整合租户资源表和存储使用大小表（实际在租户化配表实体中进行）
     * 两个表连接有两种情况之一：
     * 1.要么是ipAddr和typeName对应存储使用表的keyword和type，
     *   ipAddr这列还有多个ip的情况，如有多个ip则累加他们的使用量
     * 2.要么是path和typeName对应存储使用表的keyword和type
     * 
     * @return List<DisTenantEntity>
     * @see
     */
    public List<DisTenantEntity> fixTrmAndRum(){
        List<DisTenantEntity> trmAndRum = disTenantDao.basicOfDisTenant();
        //1.通过IpAddr和resource_type和resource_usage_mid_entity表进行连接
        for(DisTenantEntity entity:trmAndRum){
            if (entity.getIpAddr() != null && entity.getResourceType() != null) {
                String[] ip = entity.getIpAddr().split(",");
                Double totalUse = 0.0;
                boolean flag = false;  //标记有没有查到数据
                String typename = entity.getResourceType();
                for(int i=0; i<ip.length; i++){ //每个ip进行联合查询，累加结果为该租户该记录的存储已使用量
                    String temp = disTenantDao.findStoUsageByIpAndType(ip[i], typename);
                    if (temp != null) {
                        totalUse += Double.parseDouble(temp);
                        flag = true;
                    }
                }
                if (flag) { //如果表连接有查到数据，才设置这项，若没有则依旧置空
                    entity.setStorageUsage(totalUse+"");
                }
            }
        }
        //2.通过path和resource_type和resource_usage_mid_entity表进行连接
        for(DisTenantEntity entity:trmAndRum){
            if (entity.getPath() != null && entity.getResourceType() != null) {
                String path = entity.getPath();
                String typename = entity.getResourceType();
                Double totalUse = 0.0;
                boolean flag = false;  //标记有没有查到数据
                String temp = disTenantDao.findStoUsageByPathAndType(path, typename);
                if (temp != null) {
                    totalUse += Double.parseDouble(temp);
                    flag = true;
                }
                if (flag) { //如果表连接有查到数据，才设置这项，若没有则依旧置空
                    entity.setStorageUsage(totalUse+"");
                }
            }
        }
        
        return trmAndRum;
    }
}
