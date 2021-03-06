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
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bonc.nerv.tioa.week.constant.TioaConstant;
import com.bonc.nerv.tioa.week.dao.TenantResourceMidDao;
import com.bonc.nerv.tioa.week.dao.TenretiredDao;
import com.bonc.nerv.tioa.week.entity.SearchTenretiredData;
import com.bonc.nerv.tioa.week.entity.TenantResourceMidEntity;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
import com.bonc.nerv.tioa.week.service.TenretiredService;
import com.bonc.nerv.tioa.week.util.DateUtils;
import com.bonc.nerv.tioa.week.util.PoiNewUtil;
import com.bonc.nerv.tioa.week.util.PoiUtils;
import com.bonc.nerv.tioa.week.util.ResultPager;
import com.bonc.nerv.tioa.week.util.SortList;

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
     * 租户资源dao
     */
    @Autowired
    private TenantResourceMidDao tenantResourceMidDao;
    /**
     * 
     * Description: <br>
     * 在查询退租信息之前，先用租户周边表里的数据更新一遍退租信息表
     * @see
     */
    private void beforefindList(){
        List<TenretiredEntity> entities = tenretiredDao.findRefreshTenretired();
        tenretiredDao.save(entities);
    }
    
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
        beforefindList();
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
                
                if (StringUtils.isNotBlank(bean.getServiceType())) {
                    predicates.add(cb.like(root.<String> get("serviceType"), "%" + bean.getServiceType() + "%"));
                }
                
                if (StringUtils.isNotBlank(bean.getTenantName())) {
                    predicates.add(cb.like(root.<String> get("tenantName"), "%" + bean.getTenantName() + "%"));
                }
                
                if (bean.getTenantLevel()!=null) {
                    predicates.add(cb.equal(root.<Integer> get("tenantLevel"), bean.getTenantLevel()));
                }
                
                if (StringUtils.isNotBlank(bean.getTenantBoss())) {
                    predicates.add(cb.like(root.<String> get("tenantBoss"), "%" + bean.getTenantBoss() + "%"));
                }
                
                if (StringUtils.isNotBlank(bean.getTenantTel())) {
                    predicates.add(cb.like(root.<String> get("tenantTel"), "%" + bean.getTenantTel() + "%"));
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
    public void deleteByTlId(Long tlId){
        //1、在退租表里删除
        TenretiredEntity tenretiredEntity = tenretiredDao.findOne(tlId);
        tenretiredDao.delete(tenretiredEntity );
        //2、在租户资源表里标记为 删除
        TenantResourceMidEntity entity = tenantResourceMidDao.findOne(tenretiredEntity.getRresId());
        entity.setState(TioaConstant.RESOURCE_STATE_DELETE);
        tenantResourceMidDao.save(entity);
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
            String resourceType=tenretiredEntity.getResourceType();
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
            String changeDate=tenretiredEntity.getChangeDate()==null?"":DateUtils.formatDateToString(tenretiredEntity.getChangeDate(),"yyyyMMdd");
            String endRentDate=tenretiredEntity.getEndRentDate()==null?"":DateUtils.formatDateToString(tenretiredEntity.getEndRentDate(),"yyyyMMdd");
            String tenantInterface=tenretiredEntity.getTenantInterface();
            String remark=tenretiredEntity.getRemark();
            if (tenretiredEntity.getTenantLevel()== null) {
                tenantLevel = ""; 
            } else if (tenretiredEntity.getTenantLevel()== 0) {
                tenantLevel = "小"; 
            } else if (tenretiredEntity.getTenantLevel()== 1) {
                tenantLevel = "中"; 
            } else if (tenretiredEntity.getTenantLevel()== 2) {
                tenantLevel = "大"; 
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
    
    @Override
    public void getExcelNew(List<TenretiredEntity> list, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "租户退租情况导出.xlsx";
        String sheetName = "租户退租情况";
        String[] headers = {"序号","服务类型","租户","租户级别","租户负责人","联系电话","资源类型","访问IP","主机数量",
            "存储使用量","计算资源","机房","统一平台数量","4A数量","需求","服务名","队列名","申请日期","开放日期",
            "变更时间","退租时间","平台接口人","备注"};
        SortList<TenretiredEntity> asort = new SortList<TenretiredEntity>();
        asort.Sort(list, "getTenantName", "desc");
        int index = 1;
        List<List<String[]>> ttEntityLists = new ArrayList<List<String[]>>();
        Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
        for(TenretiredEntity teEntity : list){
            int countTy = tenretiredDao.countType(teEntity.getTenantId(), "统一平台");//计算统一平台数量
            int count4A = tenretiredDao.countType(teEntity.getTenantId(), "4A");//计算4A数量
            String tenantLevel=teEntity.getTenantLevel()==null?"":String.valueOf(teEntity.getTenantLevel());
            String resourceType=teEntity.getResourceType()==null?"":String.valueOf(teEntity.getResourceType());
            String hostNum=countNum(teEntity.getAskIp())==0?"":String.valueOf(countNum(teEntity.getAskIp()));
            //根据资源类型判断是否需计算主机数量
            if(checkResourceType(resourceType)){
                hostNum = "*";
            }
            String computingResourceRate=teEntity.getComputingResourceRate()==null?"":String.valueOf(teEntity.getComputingResourceRate());
            String uniplatformNum=countTy == 0?"":String.valueOf(countTy);
            String numOf4a=count4A == 0?"":String.valueOf(count4A);
            String askDate=teEntity.getAskDate()==null?"":DateUtils.formatDateToString(teEntity.getAskDate(),"yyyyMMdd");
            String openDate=teEntity.getOpenDate()==null?"":DateUtils.formatDateToString(teEntity.getOpenDate(),"yyyyMMdd");
            String changeDate=teEntity.getChangeDate()==null?"":DateUtils.formatDateToString(teEntity.getChangeDate(),"yyyyMMdd");
            String endRentDate=teEntity.getEndRentDate()==null?"":DateUtils.formatDateToString(teEntity.getEndRentDate(),"yyyyMMdd");
            
            if (teEntity.getTenantLevel()== null) {
                tenantLevel = ""; 
            } else if (teEntity.getTenantLevel()== 0) {
                tenantLevel = "小"; 
            } else if (teEntity.getTenantLevel()== 1) {
                tenantLevel = "中"; 
            } else if (teEntity.getTenantLevel()== 2) {
                tenantLevel = "大"; 
            } 
            String[] tenStr ={String.valueOf(index),
                teEntity.getServiceType(),
                teEntity.getTenantName(),
                tenantLevel,
                teEntity.getTenantBoss(),
                teEntity.getTenantTel(),
                resourceType,
                teEntity.getAskIp(),
                hostNum, //8
                teEntity.getStorage(),
                computingResourceRate,
                teEntity.getComputeRoom(),
                uniplatformNum,
                numOf4a,
                teEntity.getDemand(),
                teEntity.getServiceName(),
                teEntity.getSequenceName(),
                askDate,
                openDate,
                changeDate,
                endRentDate,
                teEntity.getTenantInterface(),
                teEntity.getRemark()
            };
            String tenantName = teEntity.getTenantName();
            if(map.containsKey(tenantName)){
                if(!resourceType.equals("统一平台")&& !resourceType.equals("4A")){
                    map.get(tenantName).add(tenStr);
                } 
            } else{
                List<String[]> newList = new ArrayList<String[]>();
                if(!resourceType.equals("统一平台")&& !resourceType.equals("4A")){
                    newList.add(tenStr);
                    ttEntityLists.add(newList);
                    map.put(tenantName, newList);
                    index++;
                } 
            }
            
        }
        
        Integer[] mergeClom = {0 , 1, 2, 3, 4, 5, 12, 13, 14, 21};
        XSSFWorkbook workbook = PoiNewUtil.createWorkBook(sheetName, headers ,ttEntityLists, mergeClom );
        
        
        final String userAgent = request.getHeader("USER-AGENT");
        if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
            fileName = URLEncoder.encode(fileName,"UTF-8");
        }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
            fileName = new String(fileName.getBytes(), "ISO8859-1");
        }else{
            fileName = URLEncoder.encode(fileName,"UTF-8");//其他浏览器
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        workbook.write(response.getOutputStream());
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
        //response.getOutputStream().close();
        System.out.println("excel导出成功！");
    }
    
    /**
     * 计算主机数量
     * @param string ip地址字符串
     * @return 主机数量
     */
    public static int countNum(String string){
        
        int count = 0;//主机数量
        int start =0;//起始位置
        int end = 0;//结束位置
        
        //判断IP地址非空，且包含数字
        if(string!=null && string.matches(".*\\d+.*")){
            //字符串包含','
            if(string.contains(",")){
                //字符串包含'~'和'/'
                if(string.contains("~") && string.contains("/")){
                   
               
                } else if(string.contains("~")){ //字符串只包含'~'
                    String[] strArray = string.split(",");
                    for(int i = 0;i < strArray.length;i++){
                        for(int j = strArray[i].length()-1;j >= 0 ;j--) {
                            if(strArray[i].charAt(j) == '~'){
                                try {
                                    end = Integer.valueOf(strArray[i].substring(j+1));
                                } catch (Exception e) {
                                }
                                
                            }
                            if(strArray[i].charAt(j) =='.'){
                                int index = strArray[i].indexOf('~');
                                if(start ==0){ 
                                    try {
                                        start = Integer.valueOf(strArray[i].substring(j+1,index)); 
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                        count = count + end - start +1;
                        start = 0;
                        end = 0;
                    }
                } else if(string.contains("/")){//字符串只包含'/'
                    
                } else {
                    count=1;
                    for(int i = 0;i < string.length();i++){
                        if(string.charAt(i)==','){
                            count++;
                        }
                    }
                }
            } else if(string.contains("~")){
                for (int i=string.length()-1;i >= 0;i--){
                    if(string.charAt(i)=='~'){
                        try {
                            end = Integer.valueOf(string.substring(i+1));
                        } catch (Exception e) {
                        }
                        
                    }
                    if(string.charAt(i)=='.'){
                        int index = string.indexOf('~');
                        if(start ==0){ 
                            try {
                                start = Integer.valueOf(string.substring(i+1,index)); 
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                count = end-start+1;
            } else if(string.contains("/")){
                for (int i=string.length()-1;i >= 0;i--){
                    if(string.charAt(i)=='/'){
                        try {
                            end = Integer.valueOf(string.substring(i+1));
                        } catch (Exception e) {
                        }
                    }
                    if(string.charAt(i)=='.'){
                        int index = string.indexOf('/');
                        if(start ==0){
                            try {
                                start = Integer.valueOf(string.substring(i+1,index));
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                count = end-start+1;
            } else {
                count=1;
            }
        }
        if(count < 0){
            count = 0;
        }
        return count;
    }
    
    
    /**
     * 检查资源类型
     * @param type 资源类型
     * @return 返回值
     * @see
     */
    public static Boolean checkResourceType(String type){
        Boolean result = false;
        //资源类型
        String[] resource = {"Hive","Oracle","FTP集群","Spark","Flume","mysql","FTP资源"};
        for(int i = 0;i < resource.length;i++){
            if(resource[i].equalsIgnoreCase(type)){
                result = true;
            }
        }
        return result;
    }
}
