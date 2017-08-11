/*
 * 文件名：TenretiredServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年8月3日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.bonc.nerv.tioa.week.dao.TenretiredDao;
import com.bonc.nerv.tioa.week.entity.SearchTenretiredData;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
import com.bonc.nerv.tioa.week.service.TenretiredService;
import com.bonc.nerv.tioa.week.util.DateUtils;
import com.bonc.nerv.tioa.week.util.PoiUtils;
import com.bonc.nerv.tioa.week.util.ResultPager;

/**
 * 
 * 
 * @author ymm
 * @version 2017年8月3日
 * @see TenretiredServiceImpl
 * @since
 */
@Service("tenretiredService")
public class TenretiredServiceImpl implements  TenretiredService{
   
    /**
     * 引入dao层
     */
    @Autowired
    private TenretiredDao  tenretiredDao;
    
    /**
     * Description:添加分页的查询
     * @param searchData 查询条件对象
     * @param start
     * @param length
     * @param draw
     * @return  查询结果
     * @see
     */
    @Override
    public String findTenretiredList(SearchTenretiredData searchData, Integer start,
                                     Integer length, String draw){
        PageRequest pageRequest=null;
        Map<String,Object> resultMap=new HashMap<>();
        if(start==null){
            pageRequest=ResultPager.buildPageRequest(start, length);
        }else{
            pageRequest=ResultPager.buildPageRequest(start/length+1, length);
        }
        
        //根据查询条件查询
        Specification<TenretiredEntity> querySpecifi=tenretiredSearch(searchData);
        //分页
        Page<TenretiredEntity> tenList=this.tenretiredDao.findAll(querySpecifi,pageRequest);
        resultMap.put("draw",draw);
        resultMap.put("recoredsTotal",tenList.getTotalElements());
        resultMap.put("recordsFiltered", tenList.getTotalElements());
        resultMap.put("data", tenList.getContent());
        return JSON.toJSONString(resultMap,SerializerFeature.DisableCircularReferenceDetect);
    }
    
    /**
     * Description: 多条件查询方法
     * @param bean  查询条件对象
     * @return 返回查询条件
     * @see
     */
    @Override
    public Specification<TenretiredEntity> tenretiredSearch(SearchTenretiredData bean){
        //封装查询参数
        Specification<TenretiredEntity> querySpecifi=new Specification<TenretiredEntity>(){
      
      //内部类
            @Override
            public Predicate toPredicate(Root<TenretiredEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(bean.getTenantName())) {
                    predicates.add(cb.like(root.<String> get("tenantName"), "%" + bean.getTenantName() + "%"));
                }

                if (StringUtils.isNotBlank(bean.getTenantInterface())) {
                    predicates.add(cb.like(root.<String> get("tenantInterface"), "%" + bean.getTenantInterface() + "%"));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        };
        return querySpecifi;
    }
    
    /**
     * 根据条件导出用户记录信息
     * @param searchData 查询实体对象
     * @return 集合
     * @see
     */
    @Override
    public List<TenretiredEntity> exportTenretired(SearchTenretiredData searchData){
        Specification<TenretiredEntity> querySpecifi = tenretiredSearch(searchData);
        return tenretiredDao.findAll(querySpecifi);
    }
    
    /**
     * 
     * Description: 保存新增退租用户
     * @param tenretiredEntity  已退租户实体类
     * @return 返回添加结果
     * @see
     */
    @Override
    public String save(TenretiredEntity tenretiredEntity){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            tenretiredDao.save(tenretiredEntity);
            map.put("status", "200");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("status", "400");
        }
       
        return JSON.toJSONString(map);
    }
    
    /**
     * 
     * Description: 保存新增退租用户
     * @param tenretiredEntity 已退租户实体类
     * @return 返回更新结果
     * @see
     */
    @Override
    public String update(TenretiredEntity tenretiredEntity) {
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            TenretiredEntity tens=tenretiredDao.findOne(tenretiredEntity.getTlId());
            tens.setServiceType(tenretiredEntity.getServiceType());
            tens.setTenantName(tenretiredEntity.getTenantName());
            tens.setTenantLevel(tenretiredEntity.getTenantLevel());
            tens.setTenantBoss(tenretiredEntity.getTenantBoss());
            tens.setTenantTel(tenretiredEntity.getTenantTel());
            tens.setResourceType(tenretiredEntity.getResourceType());
            tens.setAskIp(tenretiredEntity.getAskIp());
            tens.setHostNum(tenretiredEntity.getHostNum());
            tens.setStorage(tenretiredEntity.getStorage());
            tens.setComputingResourceRate(tenretiredEntity.getComputingResourceRate());
            tens.setComputeRoom(tenretiredEntity.getComputeRoom());
            tens.setUniplatformNum(tenretiredEntity.getUniplatformNum());
            tens.setNumOf4a(tenretiredEntity.getNumOf4a());
            tens.setDemand(tenretiredEntity.getDemand());
            tens.setServiceName(tenretiredEntity.getServiceName());
            tens.setSequenceName(tenretiredEntity.getSequenceName());
            tens.setAskDate(tenretiredEntity.getAskDate());
            tens.setOpenDate(tenretiredEntity.getOpenDate());
            tens.setChangeDate(tenretiredEntity.getChangeDate());
            tens.setEndRentDate(tenretiredEntity.getEndRentDate());
            tens.setTenantInterface(tenretiredEntity.getTenantInterface());
            tens.setRemark(tenretiredEntity.getRemark());
            tenretiredDao.save(tens);
            map.put("status", "200");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("status", "400");
        }
       
        return JSON.toJSONString(map);
    }
    
    /**
     * 
     * 根据id删除数据
     * @param tlId 表id
     * @return 返回验证结果
     * @see
     */
    @Override
    public boolean validateByTlId(long tlId){
        int num = tenretiredDao.findByTlId(tlId);
        return num == 0 ? false : true;
    }
    
    /**
     * Description: 根据tlId得到一条记录
     * @param tlId  表id
     * @return  实体信息
     * @see
     */
    @Override
    public TenretiredEntity findOne(long tlId){
        return tenretiredDao.findOne(tlId);
    }
    
    /**
     * 删除一条已退租户记录
     * @param tlId 
     * @see
     */
    @Override
    public void delete(long tlId){
        TenretiredEntity tenretiredEntity = tenretiredDao.findOne(tlId);
        tenretiredDao.delete(tenretiredEntity );
    }
    
    /**
     * 导出Excel方法
     * @param list 返回集合
     * @param response  
     * @see
     */
    @Override
    public void getExcel(List<TenretiredEntity> list, HttpServletResponse response){
        try {
            String[] headers={"序号","服务类型","租户","租户级别","租户负责人","联系电话","资源类型","访问IP","主机数量","存储使用量","计算资源","机房","统一平台数量","4A数量","需求","服务名","队列名","申请日期","开放日期","变更时间","退租时间","平台接口人","备注"};
            List<String[]> dataset=getTenList(list);
            PoiUtils.exportExelMerge("能力开放平台已退租户情况.xlsx", headers, dataset, true, response, 
                                         new Integer[] {5,4,2}, new Integer[] {1,2,3,4,5,13,14,15,21}, new Integer[] {0}, new Integer[]{0});
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        
    }
    
    /**
     * Description: 数据放到list集合中
     * @param list
     * @return 
     * @see
     */
    @Override
    public List<String[]> getTenList(List<TenretiredEntity> list){
        List<String[]> dataset=new ArrayList<String[]>();
        for(int i=0,size=list.size();i<size;i++){
            TenretiredEntity tenretiredEntity=list.get(i);
            String serviceType=tenretiredEntity.getServiceType();
            String tenantName=tenretiredEntity.getTenantName();
            String tenantLevel=tenretiredEntity.getTenantLevel()==null?"":Integer.toString(tenretiredEntity.getTenantLevel());
            String tenantBoss=tenretiredEntity.getTenantBoss();
            String tenantTel=tenretiredEntity.getTenantTel();
            String resourceType=tenretiredEntity.getResourceType()==null?"":Integer.toString(tenretiredEntity.getTenantLevel());
            String askIp=tenretiredEntity.getAskIp();
            String hostNum=tenretiredEntity.getHostNum()==null?"":Integer.toString(tenretiredEntity.getHostNum());
            String storage=tenretiredEntity.getStorage();
            String computingResourceRate=tenretiredEntity.getComputingResourceRate()==null?"":Double.toString(tenretiredEntity.getComputingResourceRate());
            String computeRoom=tenretiredEntity.getComputeRoom();
            String uniplatformNum=tenretiredEntity.getUniplatformNum()==null?"":Integer.toString(tenretiredEntity.getUniplatformNum());
            String numOf4a=tenretiredEntity.getNumOf4a()==null?"":Integer.toString(tenretiredEntity.getNumOf4a());
            String demand=tenretiredEntity.getDemand();
            String serviceName=tenretiredEntity.getServiceName();
            String sequenceName=tenretiredEntity.getSequenceName();
            String askDate=tenretiredEntity.getAskDate()==null?"":DateUtils.formatDateToString(tenretiredEntity.getAskDate(),"yyyyMMdd");
            String openDate=tenretiredEntity.getOpenDate()==null?"":DateUtils.formatDateToString(tenretiredEntity.getOpenDate(),"yyyyMMdd");
            String changeDate=tenretiredEntity.getChangeDate();
            String endRentDate=tenretiredEntity.getEndRentDate()==null?"":DateUtils.formatDateToString(tenretiredEntity.getEndRentDate(),"yyyyMMdd");
            String tenantInterface=tenretiredEntity.getTenantInterface();
            String remark=tenretiredEntity.getRemark();
            switch (tenretiredEntity.getTenantLevel()) {
                case 0:
                    tenantLevel= "小";
                    break;
                case 1:
                    tenantLevel= "中";
                    break;
                case 2:
                    tenantLevel= "大";
                    break;
                default:
                    break;
            }
            
            switch (tenretiredEntity.getResourceType()) {
                case 1:
                    resourceType= "Fiume";
                    break;
                case 2:
                    resourceType= "FTP集群";
                    break;
                case 3:
                    resourceType= "Hbase";
                    break;
                case 4:
                    resourceType= "hue";
                    break;
                case 5:
                    resourceType= "Hive";
                    break;
                case 6:
                    resourceType= "IMPALA";
                    break;
                case 7:
                    resourceType= "KAFKA";
                    break;
                case 8:
                    resourceType= "MPP";
                    break;
                case 9:
                    resourceType= "Mysql";
                    break;
                case 10:
                    resourceType= "Oracle";
                    break;
                case 11:
                    resourceType= "Redis";
                    break;
                case 12:
                    resourceType= "spark";
                    break;
                case 13:
                    resourceType= "storm";
                    break;
                case 14:
                    resourceType= "接口机";
                    break;
                case 15:
                    resourceType= "虚拟机";
                    break;
                case 16:
                    resourceType= "物理机";
                    break;
                case 17:
                    resourceType= "应用服务器";
                    break;
                default:
                    break;
            }
            String[] service={Integer.toString(i+1),serviceType,tenantName,tenantLevel,tenantBoss,
                              tenantTel,resourceType,askIp,hostNum,storage,computingResourceRate,
                              computeRoom,uniplatformNum,numOf4a,demand,serviceName,sequenceName,askDate,openDate,
                              changeDate,endRentDate,tenantInterface,remark};
            dataset.add(service);
        }
        return dataset;
    }
    
    /**
     * 获取中间表数据到tioa_tenant_leave_show表
     */
    @Override
    public void getMidDateToTtl() {
        /**
         * 调用接口更新中间表数据
         * 
         * 将中间表数据汇总到展示表中
         * （但是，只将中间表中日期大于展示表中日期的数据进行计算和汇总）
         */

    }
}
