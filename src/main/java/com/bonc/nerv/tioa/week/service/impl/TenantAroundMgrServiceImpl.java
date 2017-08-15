/*
 * 文件名：TenantAroundMgrService.java 
 * 版权：Copyright by www.bonc.com.cn 
 * 描述： 修改人：leijin 
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
import com.bonc.nerv.tioa.week.entity.TioaTenantAroundShowEntity;
import com.bonc.nerv.tioa.week.service.TenantAroundMgrService;
import com.bonc.nerv.tioa.week.util.POIUtil;
import com.bonc.nerv.tioa.week.util.PoiUtils;
import com.bonc.nerv.tioa.week.util.WebClientUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 租户周边信息管理服务层实现类
 * 
 * @author leijin
 * @version 2017年8月7日
 * @see TenantAroundMgrServiceImpl
 * @since
 */
@Service("tenantAroundMgrService")
public class TenantAroundMgrServiceImpl implements TenantAroundMgrService {

    /**
     * 
     */
    @Autowired
    private TenantAroundMgrDao tenantAroundMgrDao;

    /**
     * 从该接口获取数据
     */
    @Value("${bonc.restful.findAllAroundTenant}")
    private String findAllAroundTenant;

    /**
     * 从接口导入数据到数据库
     * 
     */
    @Override
    public void saveIdAndNameFromHttp() {
        String jsonStr = WebClientUtil.doGet(findAllAroundTenant, null);
        ObjectMapper map = new ObjectMapper();
        List<TioaTenantAroundShowEntity> listInterface = new ArrayList<TioaTenantAroundShowEntity>();
        //解析接口中的json数据
        try {
            JsonNode jsonNode = map.readTree(jsonStr);
            String success = jsonNode.get("success").toString();
            if (!success.equals("true")) {
                return;
            }
            JsonNode dataNode = jsonNode.get("data");
            for (JsonNode nodeOne : dataNode) {
                TioaTenantAroundShowEntity tioaTenantAroundShowEntity = new TioaTenantAroundShowEntity();
                tioaTenantAroundShowEntity.setTenantId(nodeOne.get("tenantId").asText());
                tioaTenantAroundShowEntity.setTenantName(nodeOne.get("tenantName").asText());
                listInterface.add(tioaTenantAroundShowEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取数据库中的数据与接口中的数据进行比较
        List<TioaTenantAroundShowEntity> listFromDB = this.findAllTenantAroundMgr();
        
        //用从接口获取的list减去从数据库获取的list，将差集添加到从数据库获取的list
        int temp = 0;
        for (int i = 0;i < listInterface.size(); i ++) {
            temp = 0;
            for (int j = 0; j < listFromDB.size(); j ++) {
                if (listInterface.get(i).getTenantId().equals(listFromDB.get(j).getTenantId())) {
                    temp = 1;
                    continue;
                }
            }
            if (temp == 0) {
                listFromDB.add(listInterface.get(i));
            }
        }
        // 保存到数据库
        tenantAroundMgrDao.save(listFromDB);
    }

    /**
     * 从数据库导出数据到Excel进行批量修改
     */
    public void exportFromTenantAroundMgr() {
        List<TioaTenantAroundShowEntity> list = tenantAroundMgrDao.findAll();
        POIUtil.exportExcel(list);
        System.out.println("导出数据库成功");
    }

  

    /**
     * 查询所有记录显示到页面
     * 
     * @return list
     */
    public List<TioaTenantAroundShowEntity> findAllTenantAroundMgr() {
        List<TioaTenantAroundShowEntity> list = new ArrayList<TioaTenantAroundShowEntity>();
        list = tenantAroundMgrDao.findAll();
        return list;
    }

    @Override
    public Boolean validateById(Long ttaId) {
        int num = tenantAroundMgrDao.findById(ttaId);
        return num == 0 ? false : true;
    }

    /**
     * 删除一条记录
     * @param ttaId   
     */
    public void deleteTenantAroundMgr(Long ttaId) {
        tenantAroundMgrDao.delete(ttaId);
    }

    /**
     * 修改后保存一条记录到数据库
     * 
     * @param tioaTenantAroundShowEntity  
     */
    public void saveTenantAroundMgr(TioaTenantAroundShowEntity tioaTenantAroundShowEntity) {
        tenantAroundMgrDao.save(tioaTenantAroundShowEntity);
    }

    /**
     * Description: 导出Excel
     * 
     * @return list
     * @see
     */
    public List<TioaTenantAroundShowEntity> exportTenretired() {
        List<TioaTenantAroundShowEntity> list = tenantAroundMgrDao.findAll();
        return list;
    }

    /**
     * 导出Excel方法
     * 
     * @param list
     *            返回集合
     * @param response
     * @see
     */
    @Override
    public void getExcel(List<TioaTenantAroundShowEntity> list, HttpServletResponse response) {
        try {
            String[] headers = {"租户id", "租户名", "租户级别", "租户负责人", "联系电话", "统一平台个数", "4A个数",
                "需求", "平台接口人"};
            List<String[]> dataset = getTenList(list);
            PoiUtils.setAddress("A:I");
            PoiUtils.exportExelMerge("能力开放平台周边信息情况表.xlsx", headers, dataset, true, response,
                new Integer[] {}, new Integer[] {}, new Integer[] {}, new Integer[] {});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Description: 数据放到list集合中
     * 
     * @param list  
     * @return list
     * @see
     */
    private List<String[]> getTenList(List<TioaTenantAroundShowEntity> list) {
        List<String[]> dataset = new ArrayList<String[]>();
        for (int i = 0, size = list.size(); i < size; i++ ) {
            TioaTenantAroundShowEntity tioaTenantAroundShowEntity = list.get(i);
            String tenantId = tioaTenantAroundShowEntity.getTenantId();
            String tenantName = tioaTenantAroundShowEntity.getTenantName();
            String tenantLevel = getLevalStr(tioaTenantAroundShowEntity.getTenantLevel());
            String tenantBoss = tioaTenantAroundShowEntity.getTenantBoss();
            String tenantTel = tioaTenantAroundShowEntity.getTenantTel();
            String numOfUniplatformNum = tioaTenantAroundShowEntity.getNumOfUnifiedPlatform() == null ? "" : Integer.toString(
                tioaTenantAroundShowEntity.getNumOfUnifiedPlatform());
            String numOf4a = tioaTenantAroundShowEntity.getNumOf4a() == null ? "" : Integer.toString(
                tioaTenantAroundShowEntity.getNumOf4a());
            String tenantReqirement = tioaTenantAroundShowEntity.getTenantReqirement();
            String tenantInterface = tioaTenantAroundShowEntity.getTenantInterface();
            if (tioaTenantAroundShowEntity.getTenantLevel()== null) {
                tenantLevel = ""; 
            } else if (tioaTenantAroundShowEntity.getTenantLevel()== 0) {
                tenantLevel = "小"; 
            } else if (tioaTenantAroundShowEntity.getTenantLevel()== 1) {
                tenantLevel = "中"; 
            } else if (tioaTenantAroundShowEntity.getTenantLevel()== 2) {
                tenantLevel = "大"; 
            } 
            String[] service = {tenantId, tenantName, tenantLevel,
                tenantBoss, tenantTel, numOfUniplatformNum, numOf4a, tenantReqirement,
                tenantInterface};
            dataset.add(service);
        }
        return dataset;
    }

    /**
     * 从数据库导出到Excel时，将租户级别转换成大中小
     * @param level  
     * @return 大，中，小
     * @see
     */
    private String getLevalStr(Integer level) {
        if (level == com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_LEVEL_LARGE) {
            return "大";
        } else if (level == com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_LEVEL_MIDDLE) {
            return "中";
        } else if (level == com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_LEVEL_SMALL) {
            return "小";
        } else {
            return "";
        }
    }
    
    /**
     * 将文件导入到数据库
     */
    @Override
    public void saveExcel(MultipartFile excelFile)
        throws ParseException {
        List<String[]> list = null;
        try {
            list = PoiUtils.readExcel(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++ ) {

            TioaTenantAroundShowEntity tioaTenantAroundShow = new TioaTenantAroundShowEntity();
            // 租户
            if (list.get(i)[0] == null || 0 == list.get(i)[0].length()) {
                continue;
            }
            tioaTenantAroundShow.setTenantId(list.get(i)[0]);
            tioaTenantAroundShow.setTenantName(list.get(i)[1]);
            tioaTenantAroundShow.setTenantLevel(getLevalInteger(list.get(i)[2]));
            tioaTenantAroundShow.setTenantBoss(list.get(i)[3]);
            tioaTenantAroundShow.setTenantTel(list.get(i)[4]);
            tioaTenantAroundShow.setNumOfUnifiedPlatform(getInteger(list.get(i)[5]));
            tioaTenantAroundShow.setNumOf4a(getInteger(list.get(i)[6]));
            tioaTenantAroundShow.setTenantReqirement(list.get(i)[7]);
            tioaTenantAroundShow.setTenantInterface(list.get(i)[8]);
            tenantAroundMgrDao.save(tioaTenantAroundShow);
        }

    }

    /**
     * 字符串转整形
     * 
     * @param str  
     * @return int
     * @see
     */
    public Integer getInteger(String str) {
        if (str != null && !str.equals("")) {
            return Integer.parseInt(str);
        } else {
            return null;
        }
    }
    
    /**
     * 将用户级别转换成整形
     * @param str  
     * @return int 
     * @see
     */
    public Integer getLevalInteger(String str) {
        if (str.equals("大")) {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_LEVEL_LARGE;
        } else if (str.equals("中")) {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_LEVEL_MIDDLE;
        } else if (str.equals("小")) {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_LEVEL_SMALL;
        } else {
            return null;
        }
    }
    

    /**
     * 获取一条修改数据
     */
    @Override
    public TioaTenantAroundShowEntity updateTenantAroundMg(Long ttaId) {
        return tenantAroundMgrDao.findOne(ttaId);
    }


    @Override
    public void deleteAll(){
        tenantAroundMgrDao.deleteAll();
    }

}
