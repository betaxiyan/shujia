/*
 * 文件名：TenantAroundMgrService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月7日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.week.dao.TenantAroundMgrDao;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;
import com.bonc.nerv.tioa.week.entity.TioaTenantChargingShow;
import com.bonc.nerv.tioa.week.service.TenantAroundMgrService;
import com.bonc.nerv.tioa.week.util.POIUtil;
import com.bonc.nerv.tioa.week.util.PoiUtils;
import com.bonc.nerv.tioa.week.util.WebClientUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 租户周边信息管理服务层实现类
 * @author leijin
 * @version 2017年8月7日
 * @see TenantAroundMgrServiceImpl
 * @since
 */
@Service("tenantAroundMgrService")
public class TenantAroundMgrServiceImpl implements TenantAroundMgrService{

    @Autowired
    private TenantAroundMgrDao tenantAroundMgrDao;
    
    @Value("${bonc.restful.findAllAroundTenant}")
    private String findAllAroundTenant;
    
    /**
     * 从接口导入数据到数据库
     */
    @Override
    public void saveIdAndNameFromHttp() {
        String jsonStr = WebClientUtil.doGet("findAllAroundTenant",null);
        ObjectMapper map = new ObjectMapper();
        List<TioaTenantAroundShowEntity>list = new ArrayList<TioaTenantAroundShowEntity>();
        try {
            JsonNode jsonNode = map.readTree(jsonStr);
            String success = jsonNode.get("success").toString();
            if (!success.equals("true")) {
                return ;
            } 
            JsonNode dataNode = jsonNode.get("data");
            for (JsonNode nodeOne : dataNode) {
                TioaTenantAroundShowEntity tioaTenantAroundShowEntity = new TioaTenantAroundShowEntity();
                tioaTenantAroundShowEntity.setTenantId(nodeOne.get("tenantId").asText());
                System.out.println(nodeOne.get("tenantId").asText());
                tioaTenantAroundShowEntity.setTenantName(nodeOne.get("tenantName").asText());
                list.add(tioaTenantAroundShowEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //保存到数据库
        tenantAroundMgrDao.save(list);
        System.out.println("保存到数据库成功");
    }
    
    /**
     * 从数据库导出数据到Excel进行批量修改
     */
    public void exportFromTenantAroundMgr(){
        List<TioaTenantAroundShowEntity> list = tenantAroundMgrDao.findAll();
        POIUtil.exportExcel(list);
        System.out.println("导出数据库成功");
    }
    
    /**
     * 将批量修改的Excel导入到数据库
     */
    public void importToTenantAroundMgr(){
        
    }
    
    /**
     * 查询所有记录显示到页面
     * @return list
     */
    public List<TioaTenantAroundShowEntity> findAllTenantAroundMgr(){
        List<TioaTenantAroundShowEntity> list = new ArrayList<TioaTenantAroundShowEntity>();
        list = tenantAroundMgrDao.findAll();
        return list;
    }
    
    @Override
    public Boolean validateById(Long ttaId) {
        int num = tenantAroundMgrDao.findById(ttaId);
        return num == 0 ? false : true ;
    }
    
    /**
     * 删除一条记录
     * @param  tioaTenantAroundShowEntity  
     */
    public void deleteTenantAroundMgr(Long  ttaId){
        tenantAroundMgrDao.delete(ttaId);
    }
    
    /**
     * 修改后保存一条记录到数据库
     * @param tioaTenantAroundShowEntity  
     */
    public void saveTenantAroundMgr(TioaTenantAroundShowEntity tioaTenantAroundShowEntity) {
        tenantAroundMgrDao.save(tioaTenantAroundShowEntity);
    }
    
    
    /**
     * Description: 导出Excel
     * @return  list
     * @see
     */
    public List<TioaTenantAroundShowEntity>  exportTenretired(){
        List<TioaTenantAroundShowEntity> list=tenantAroundMgrDao.findAll();
        return list;
    }
    
    /**
     * 导出Excel方法
     * @param list 返回集合
     * @param response  
     * @see
     */
    @Override
    public void getExcel(List<TioaTenantAroundShowEntity> list, HttpServletResponse response){
        try {
            String[] headers={"序号","租户id","租户名","租户级别","租户负责人","联系电话","统一平台个数","4A个数","需求","平台接口人"};
            List<String[]> dataset=getTenList(list);
            PoiUtils.exportExelMerge("能力开放平台周边信息情况表.xls", headers, dataset, true, response, 
                                         new Integer[] {0}, new Integer[] {0}, new Integer[] {0}, new Integer[]{0});
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
    private List<String[]> getTenList(List<TioaTenantAroundShowEntity> list){
        List<String[]> dataset=new ArrayList<String[]>();
            for(int i=0,size=list.size();i<size;i++){
                TioaTenantAroundShowEntity tioaTenantAroundShowEntity=list.get(i);
                String tenantId=tioaTenantAroundShowEntity.getTenantId();
                String tenantName=tioaTenantAroundShowEntity.getTenantName();
                String tenantLevel=tioaTenantAroundShowEntity.getTenantLevel()==null?"":Integer.toString(tioaTenantAroundShowEntity.getTenantLevel());
                String tenantBoss=tioaTenantAroundShowEntity.getTenantBoss();
                String tenantTel=tioaTenantAroundShowEntity.getTenantTel();
                String numOfUniplatformNum=tioaTenantAroundShowEntity.getNumOfUnifiedPlatform()==null?"":Integer.toString(tioaTenantAroundShowEntity.getNumOfUnifiedPlatform());
                String numOf4a=tioaTenantAroundShowEntity.getNumOf4a()==null?"":Integer.toString(tioaTenantAroundShowEntity.getNumOf4a());
                String tenantReqirement=tioaTenantAroundShowEntity.getTenantReqirement();
                String tenantInterface=tioaTenantAroundShowEntity.getTenantInterface();
                String[] service={Integer.toString(i+1),tenantId,tenantName,tenantLevel,tenantBoss,tenantTel,numOfUniplatformNum,numOf4a,tenantReqirement,tenantInterface};
                dataset.add(service);
            }
        return dataset;
        }
     
    /**
      * 将文件导入到数据库
      */
     @Override
     public void saveExcel(MultipartFile excelFile) throws ParseException {    
        List<String[]> list = null;       
        try {
            list = POIUtil.readExcel(excelFile);
            System.out.println(list.size());       
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }      
        for (int i = 0;i < list.size();i ++) {
         
            TioaTenantAroundShowEntity  tioaTenantAroundShow = new TioaTenantAroundShowEntity();
            //租户
            if(list.get(i)[0] == null || 0 == list.get(i)[0].length()){
                continue;
            }
            tioaTenantAroundShow.setTenantId(list.get(i)[0]);
            tioaTenantAroundShow.setTenantName(list.get(i)[1]);
            tioaTenantAroundShow.setTenantLevel(getInteger(list.get(i)[2]));
            tioaTenantAroundShow.setTenantTel(list.get(i)[3]);
            tioaTenantAroundShow.setTenantBoss(list.get(i)[4]);
            tioaTenantAroundShow.setNumOfUnifiedPlatform(getInteger(list.get(i)[5]));
            tioaTenantAroundShow.setNumOf4a(getInteger(list.get(i)[6]));
            tioaTenantAroundShow.setTenantReqirement(list.get(i)[7]);
            tioaTenantAroundShow.setTenantInterface(list.get(i)[8]);
            tenantAroundMgrDao.save(tioaTenantAroundShow);
        }
   
    }

         
       /**
         * 字符串转整形
         * @param str   
         * @return  int
         * @see
         */
         public Integer getInteger(String str){
             if (str != null && !str.equals("")) {
                 return Integer.parseInt(str);
             } else {
                 return null;
             }
         }

   
}
