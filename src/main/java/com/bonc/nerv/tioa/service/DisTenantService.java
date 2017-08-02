/*
 * 文件名：UserService.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：Administrator 修改时间：2017年7月28日 跟踪单号：
 * 修改单号： 修改内容：
 */

package com.bonc.nerv.tioa.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bonc.nerv.tioa.dao.DisTenantDao;
import com.bonc.nerv.tioa.entity.DisTenantEntity;
import com.bonc.nerv.tioa.entity.SearchDisTenant;
import com.bonc.nerv.tioa.util.DateUtils;
import com.bonc.nerv.tioa.util.PoiUtils;
import com.bonc.nerv.tioa.util.ResultPager;



@Service
public class DisTenantService {

    @Autowired
    DisTenantDao distenantDao;

    /**
     * 
     * 加载列表数据并查询
     * @param start 起始页
     * @param length 每页数据
     * @param draw
     * @return ""
     * @see
     */
    public String findList(SearchDisTenant searchdisTenant,Integer start, Integer length, String draw) {
        Map<String, Object> resultMap = new HashMap<>();
        PageRequest pageRequest = null;
        if (start == null) {
            pageRequest = ResultPager.buildPageRequest(start, length);
        }
        else {
            pageRequest = ResultPager.buildPageRequest(start / length + 1, length);
        }
        Specification<DisTenantEntity> querySpecifi = fileSearch(searchdisTenant);
        Page<DisTenantEntity> pageUser = distenantDao.findAll(querySpecifi,pageRequest);
        resultMap.put("draw", draw);
        resultMap.put("recordsTotal", pageUser.getTotalElements());
        resultMap.put("recordsFiltered", pageUser.getTotalElements());
        resultMap.put("data", pageUser.getContent());
        return JSON.toJSONString(resultMap, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 查询的语句
     * 
     * @param tenantName
     * @param tenantBoss
     * @return 
     * @see
     */
    private Specification<DisTenantEntity> fileSearch(SearchDisTenant searchdisTenant) {
      //封装查询参数
        Specification<DisTenantEntity> querySpecifi = new Specification<DisTenantEntity>() {
            //内部类
            @Override
            public Predicate toPredicate(Root<DisTenantEntity> root, CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(searchdisTenant.getTenantName())){
                    predicates.add(cb.like(root.<String> get("tenantName"),"%"+searchdisTenant.getTenantName()+"%"));
                }
                if(StringUtils.isNotBlank(searchdisTenant.getTenantBoss())){
                    predicates.add(cb.like(root.<String> get("tenantBoss"),"%"+searchdisTenant.getTenantBoss()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return querySpecifi;
    }

    /**
     * 导出Excel
     * 
     * @return  list
     * @see
     */
    public List<DisTenantEntity> exportFile() {
        List<DisTenantEntity> list = distenantDao.findAll();
        return list;
    }

    /**
     * 
     * 将数据填入到处的表格
     * 
     * @param list
     * @param request
     * @param response 
     * @see
     */
    public void getExcel(List<DisTenantEntity> list, HttpServletRequest request,
                         HttpServletResponse response) {

        // 写入表头信息
        String[] headers = {"序号", "服务类型", "租户名", "租户级别", "租户负责人", "租户负责人电话", "资源类型", "文件数", "存储",
            "存储单位", "存储使用量", "存储使用量单位", "存储使用占比", "cpu核数", "cpu最大数", "cpu平均数", "内存大小", "内存最大值",
            "内存平均值", "申请时间", "变更时间", "开放时间"};

        // 添加Excel内容
        List<String[]> dataset = getList(list);
        String fileName = DateUtils.formatDateToString(new Date(), "yyyyMMddHHmmss") + ".xls";
        try {
            PoiUtils.exportExelMerge(fileName, headers, dataset, true, response,
                new Integer[] {5, 4, 2}, new Integer[] {1, 2, 3, 4, 5, 7}, new Integer[] {7},
                new Integer[] {4});
            System.out.println("excel导出成功！");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 添加excel内容
     * 
     * @param list
     * @return 
     * @see
     */
    public  List<String[]> getList(List<DisTenantEntity> list){
        List<String[]> dataset = new ArrayList<String[]>();
        for (int i = 0, size = list.size(); i < size; i++ ) {
            DisTenantEntity distenant = list.get(i);
            String tdId = String.valueOf(distenant.getTdId());
            String serviceType = distenant.getServiceType();
            String tenantName = distenant.getTenantName();
            String tenantLevel =distenant.getTenantLevel()== null ? " " :String.valueOf(distenant.getTenantLevel()) ;
            String tenantBoss = distenant.getTenantBoss();
            String tenantTel = distenant.getTenantTel();
            String resourceType =distenant.getResourceType() == null ? " ":String.valueOf(distenant.getResourceType());
            String fileCount =distenant.getFileCount() == null ? " ":String.valueOf(distenant.getFileCount()) ;
            String storage = distenant.getStorage() == null ? " " : String.valueOf(distenant.getStorage());
            String storageUnit = distenant.getStorageUnit();
            String storageUsage =distenant.getStorageUsage() == null ? " ": String.valueOf(distenant.getStorageUsage());
            String storageUsageUnit = distenant.getStorageUsageUnit();
            String storageUsageRate = Double.toString(distenant.getStorageUsageRate()) == "null" ? " ": Double.toString(distenant.getStorageUsageRate());
            String cpuNum = distenant.getCpuNum() == null ? " ":String.valueOf(distenant.getCpuNum()) ;
            String cpuMax = distenant.getCpuMax() == null ? " " : String.valueOf(distenant.getCpuMax());
            String cpuAvg = distenant.getCpuAvg() == null ? " " :String.valueOf(distenant.getCpuAvg()) ;
            String memorySize = distenant.getMemorySize() == null ? " ": String.valueOf(distenant.getMemorySize());
            String memoryMax = distenant.getMemoryMax() == null ? " ":String.valueOf(distenant.getMemoryMax());
            String memoryAvg = distenant.getMemoryAvg()== null ? " ": String.valueOf(distenant.getMemoryAvg());
            String askDate = distenant.getAskDate() == null ? " ": String.valueOf(distenant.getAskDate());
            String changeDate = distenant.getChangeDate() == null ? " " : String.valueOf(distenant.getChangeDate());
            String openDate = distenant.getOpenDate() == null ? " ":String.valueOf(distenant.getOpenDate());
            switch (distenant.getTenantLevel()) {
                case 0: 
                    tenantLevel = "小";
                    break;
                case 1:
                    tenantLevel = "中";
                    break;
                case 2:
                    tenantLevel = "大";
                    break;
                default:
                    break;
            }
            switch(distenant.getResourceType()){
                case 1:
                    resourceType = "Flume";
                    break;
                case 2:
                    resourceType = "FTP集群";
                    break;
                case 3:
                    resourceType = "Hbase";
                    break;
                case 4:
                    resourceType = "hue";
                    break;
                case 5:
                    resourceType = "Hive";
                    break;
                case 6:
                    resourceType = "IMPALA";
                    break;
                case 7:
                    resourceType = "KAFKA";
                    break;
                case 8:
                    resourceType = "MPP";
                    break;
                case 9:
                    resourceType = "Mysql";
                    break;
                case 10:
                    resourceType = "Oracle";
                    break;
                case 11:
                    resourceType = "Redis";
                    break;
                case 12:
                    resourceType = "spark";
                    break;
                case 13:
                    resourceType = "storm";
                    break;
                case 14:
                    resourceType = "接口机";
                    break;
                case 15:
                    resourceType = "虚拟机";
                    break;
                case 16:
                    resourceType = "物理裸机";
                    break;
                case 17:
                    resourceType = "应用服务器";
                    break;
            }
           
            //遍历集合，处理数据
            String[] service = {tdId, serviceType, tenantName, tenantLevel, tenantBoss, tenantTel,
                resourceType, fileCount, storage, storageUnit, storageUsage, storageUsageUnit,
                storageUsageRate, cpuNum, cpuMax, cpuAvg, memorySize, memoryMax, memoryAvg,
                askDate, changeDate, openDate};
            dataset.add(service);
            }
      return dataset;
    }
    
    /**
     * 
     * 新增内容保存
     * 
     * @param distenantEntity 
     * @return 返回JSON字符串
     * @see
     */
    public String addUserPost(DisTenantEntity distenantEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            distenantDao.save(distenantEntity);
            map.put("status", "200");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", "400");
        }
        return JSON.toJSONString(map);

    }

    /**
     * 
     * 根据id验证是否可以删除
     * 
     * @param tdId
     * @return 
     * @see
     */
    public boolean validateById(long tdId) {
        int num = distenantDao.findById(tdId);
        return num == 0 ? false : true;
    }
    
    /**
     * 
     * 进行删除操作
     * 
     * @param tdId 
     * @see
     */
    public void deleteDisTenant(long tdId) {
       DisTenantEntity dst = distenantDao.findOne(tdId);
        distenantDao.delete(dst);
        distenantDao.flush();        
    }

    /**
     * 
     * 加载需要编辑的内容
     * 
     * @param tdId
     * @return 
     * @see
     */
    public DisTenantEntity update(long tdId) {
        return distenantDao.findOne(tdId);
    }

    /**
     * 
     * 对编辑的内容进行保存
     * 
     * @param ditenantEntity
     * @return 
     * @see
     */
    public String updateP(DisTenantEntity ditenantEntity) {
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            distenantDao.save(ditenantEntity);
            map.put("status", 200);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 400);
        }
        return JSON.toJSONString(map);
    }

}
