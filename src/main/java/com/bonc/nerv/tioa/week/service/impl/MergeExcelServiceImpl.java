package com.bonc.nerv.tioa.week.service.impl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.dao.DisTenantDao;
import com.bonc.nerv.tioa.week.dao.TenretiredDao;
import com.bonc.nerv.tioa.week.dao.TioTenChaSho_2Dao;
import com.bonc.nerv.tioa.week.entity.DisTenantEntity;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
import com.bonc.nerv.tioa.week.service.MergeExcelService;
import com.bonc.nerv.tioa.week.util.DateUtils;
import com.bonc.nerv.tioa.week.util.PoiNewUtil;
import com.bonc.nerv.tioa.week.util.SavaToExcelUtils;
import com.bonc.nerv.tioa.week.util.SortList;

/*
 * 文件名：MergeExcelServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月8日
 */

/**
 * 
 * 导出Excel
 * @author zhiyong
 * @version 2017年8月8日
 * @see MergeExcelServiceImpl
 * @since
 */
@Service("mergeExcelService")
public class MergeExcelServiceImpl implements MergeExcelService{
    
    /**
     * DisTenantDao
     */
    @Autowired
    private DisTenantDao disTenantDao;
    
    /**
     * TenretiredDao
     */
    @Autowired
    private TenretiredDao tenretiredDao;
    
    /**
     * 注入到tioTenChaSho_2Dao
     */
    @Autowired
    private TioTenChaSho_2Dao tioTenChaSho_2Dao;
    
    /**
     * 全量导出
     */
    @Override
    public Boolean getExcelNew(HttpServletRequest request, HttpServletResponse response) {
        Boolean result = false;
        String fileName = "全量导出测试.xlsx";
        XSSFWorkbook weekInfoWorkbook = new XSSFWorkbook();
        XSSFSheet sheetOne = weekInfoWorkbook.createSheet("已划配租户情况");
        XSSFSheet sheetThree = weekInfoWorkbook.createSheet("租户计费情况");
        XSSFSheet sheetTwo = weekInfoWorkbook.createSheet("已退租户");
        genDisTenantExcel(weekInfoWorkbook, sheetOne);
        genTenretiredExcel(weekInfoWorkbook, sheetTwo);
        genChargingExcel(weekInfoWorkbook, sheetThree);
        try {
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
            weekInfoWorkbook.write(response.getOutputStream());
            response.setStatus(HttpServletResponse.SC_OK);
            response.flushBuffer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("excel导出成功！");
        result = true;
        return result;
    }

    
    /**
     * 
     * Description: <br>
     * 组装已划配租户信息sheet的方法
     * @param workbook XSSFWorbook
     * @param sheet 
     * @see
     */
    public void genDisTenantExcel(XSSFWorkbook workbook, XSSFSheet sheet){
        List<DisTenantEntity> disList = new ArrayList<DisTenantEntity>();//已划配租户情况
        disList = disTenantDao.findAll();//已划配租户情况
        //String sheetName = "已划配租户情况";
        String[] headers = {"序号", "服务类型", "租户名", "租户级别", "租户负责人", "租户负责人电话", "资源类型", "文件数", "存储",
            "存储使用量",  "存储使用占比", "cpu核数", "cpu最大数", "cpu平均数", "内存大小", "内存最大值",
            "内存平均值", "申请时间", "变更时间", "开放时间"};
        SortList<DisTenantEntity> asort = new SortList<DisTenantEntity>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        asort.Sort(disList, "getTenantName", "desc");
        int index = 1;
        List<List<String[]>> dtEntityLists = new ArrayList<List<String[]>>();
        Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
        for(DisTenantEntity dEntity : disList){
            String serviceType = dEntity.getServiceType();
            String tenantName = dEntity.getTenantName();
            String tenantLevel =dEntity.getTenantLevel()== null ? " " :Integer.toString(dEntity.getTenantLevel()) ;
            String tenantBoss = dEntity.getTenantBoss();
            String tenantTel = dEntity.getTenantTel();
            String resourceType =dEntity.getResourceType() == null ? " ":String.valueOf(dEntity.getResourceType());
            String fileCount =dEntity.getFileCount() == null ? " ":String.valueOf(dEntity.getFileCount()) ;
            String storage = dEntity.getStorage();
            String storageUsage =dEntity.getStorageUsage();
            String storageUsageRate = dEntity.getStorageUsageRate() ==  null  ? " ": Double.toString(dEntity.getStorageUsageRate());
            String cpuNum = dEntity.getCpuNum();
            String cpuMax = dEntity.getCpuMax() == null ? " " : String.valueOf(dEntity.getCpuMax());
            String cpuAvg = dEntity.getCpuAvg() == null ? " " :String.valueOf(dEntity.getCpuAvg()) ;
            String memorySize = dEntity.getMemorySize();
            String memoryMax = dEntity.getMemoryMax() == null ? " ":String.valueOf(dEntity.getMemoryMax());
            String memoryAvg = dEntity.getMemoryAvg()== null ? " ": String.valueOf(dEntity.getMemoryAvg());
            String askDate = dEntity.getAskDate() == null ? " ": String.valueOf(simpleDateFormat.format(dEntity.getAskDate()));
            String changeDate = dEntity.getChangeDate() == null ? " " : String.valueOf(simpleDateFormat.format(dEntity.getChangeDate()));
            String openDate = dEntity.getOpenDate() == null ? " ":String.valueOf(simpleDateFormat.format(dEntity.getOpenDate()));
           if(dEntity.getTenantLevel()== null){
               tenantLevel = "";
           }else if(dEntity.getTenantLevel() == 0){
               tenantLevel = "小";
           }else if(dEntity.getTenantLevel()==1){
               tenantLevel = "中";
           }else if(dEntity.getTenantLevel()==2){
               tenantLevel = "大";
           }
            String[] tenStr ={String.valueOf(index),
                serviceType, tenantName, tenantLevel, tenantBoss, tenantTel,
                resourceType, fileCount, storage,storageUsage,storageUsageRate, cpuNum, cpuMax, cpuAvg, memorySize, memoryMax, memoryAvg,
                askDate, changeDate, openDate
            };
            if(map.containsKey(tenantName)){
                map.get(tenantName).add(tenStr);
            } else{
                List<String[]> newList = new ArrayList<String[]>();
                newList.add(tenStr);
                dtEntityLists.add(newList);
                map.put(tenantName, newList);
                index++;
            }
            
        }
        Integer[] mergeClom = {0 , 1, 2, 3, 4, 5, 7};
        PoiNewUtil.createSheet(workbook, sheet, headers, dtEntityLists, mergeClom);
    }
    
    /**
     * 
     * Description: <br>
     * 组装租户退租情况的sheet方法
     * 
     * @param workbook XSSFWorkbook
     * @param sheet 
     * @see
     */
    public void genTenretiredExcel(XSSFWorkbook workbook, XSSFSheet sheet){
        List<TenretiredEntity> tenList = new ArrayList<TenretiredEntity>();//已退租户
        tenList = tenretiredDao.findAll();
        //String sheetName = "租户退租情况";
        String[] headers = {"序号","服务类型","租户","租户级别","租户负责人","联系电话","资源类型","访问IP","主机数量",
            "存储使用量","计算资源","机房","统一平台数量","4A数量","需求","服务名","队列名","申请日期","开放日期",
            "变更时间","退租时间","平台接口人","备注"};
        SortList<TenretiredEntity> asort = new SortList<TenretiredEntity>();
        asort.Sort(tenList, "getTenantName", "desc");
        int index = 1;
        List<List<String[]>> ttEntityLists = new ArrayList<List<String[]>>();
        Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
        for(TenretiredEntity teEntity : tenList){
            int countTy = tenretiredDao.countType(teEntity.getTenantId(), "统一平台");//计算统一平台数量
            int count4A = tenretiredDao.countType(teEntity.getTenantId(), "4A");//计算4A数量
            String tenantLevel=teEntity.getTenantLevel()==null?"":String.valueOf(teEntity.getTenantLevel());
            String resourceType=teEntity.getResourceType()==null?"":String.valueOf(teEntity.getResourceType());
            String hostNum=TenretiredServiceImpl.countNum(teEntity.getAskIp())==0?"":String.valueOf(TenretiredServiceImpl.countNum(teEntity.getAskIp()));
            //根据资源类型判断是否需计算主机数量
            if(TenretiredServiceImpl.checkResourceType(resourceType)){
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
        PoiNewUtil.createSheet(workbook, sheet, headers, ttEntityLists, mergeClom);
    }
    
    /**
     * 
     * Description: <br>
     * 组装租户计费情况的sheet的方法
     * @param workbook XSSFWorkbook
     * @param sheet 
     * @see
     */
    public void genChargingExcel(XSSFWorkbook workbook, XSSFSheet sheet){
        List<String> title = new ArrayList<String>();//租户计费情况标题
        title.add("租户");
        title.add("服务类型"); 
        title.add("租户分类");
        title.add("资源具备日期"); 
        title.add(null); 
        title.add(null); 
        title.add("计费日期确定"); 
        title.add(null); 
        title.add(null);
        title.add(null); 
        title.add(null);
        title.add(null); 
        title.add(null);
        title.add("联通引入方"); 
        title.add("引入方联系人（租户管理员）"); 
        title.add("联系方式"); 
        title.add("申请日期");
        title.add("是否签署合同");
        title.add("月租备注");
        title.add("退租时间");
        title.add("备注");
        
        List<String> columnNames = new ArrayList<String>();//租户计费情况子标题
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add("资源");
        columnNames.add("统一及4A");
        columnNames.add("数据实际具备");
        columnNames.add("入住时长"); 
        columnNames.add("月租费用（元/月）"); 
        columnNames.add("数据报价/万元"); 
        columnNames.add("起租日期"); 
        columnNames.add("试用期"); 
        columnNames.add("计费时期"); 
        columnNames.add("到期日期"); 
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add(null);
        columnNames.add(null);
        try {
            List<List<Object>> chargeList = tioTenChaSho_2Dao.getAllTioa();
            /*JSON jsonsss = (JSON)JSON.toJSON(chargeList);
            System.out.println(jsonsss);*/
            String titleCharge = "租户计费情况";
            try {
                SavaToExcelUtils.exportSheet(titleCharge, columnNames, title, chargeList, workbook, sheet);
            }catch (IOException e) {
                e.printStackTrace();
            }catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
