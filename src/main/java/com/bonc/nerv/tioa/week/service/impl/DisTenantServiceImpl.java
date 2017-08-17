package com.bonc.nerv.tioa.week.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bonc.nerv.tioa.week.dao.CpuMemoryMidDao;
import com.bonc.nerv.tioa.week.dao.DisTenantDao;
import com.bonc.nerv.tioa.week.dao.ResUsaMidDao;
import com.bonc.nerv.tioa.week.dao.TenantResourceMidDao;
import com.bonc.nerv.tioa.week.entity.CpuMemoryMidEntity;
import com.bonc.nerv.tioa.week.entity.DisTenantEntity;
import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;
import com.bonc.nerv.tioa.week.entity.SearchDisTenant;
import com.bonc.nerv.tioa.week.service.DisTenantService;
import com.bonc.nerv.tioa.week.service.ExcelAnalyseService;
import com.bonc.nerv.tioa.week.util.DateUtils;
import com.bonc.nerv.tioa.week.util.POIUtil;
import com.bonc.nerv.tioa.week.util.PoiNewUtil;
import com.bonc.nerv.tioa.week.util.PoiUtils;
import com.bonc.nerv.tioa.week.util.ResultPager;
import com.bonc.nerv.tioa.week.util.SortList;


/**
 * 
 * 已划配租户服务类
 * 
 * @author zhangwen
 * @version 2017年8月2日
 * @see DisTenantService
 * @since
 */
@Service
public class DisTenantServiceImpl implements DisTenantService{

    /*
     * distenantDao
     */
    @Autowired
    private DisTenantDao distenantDao;
    
    @Autowired
    private ExcelAnalyseService excelAnalyseService;

    @Autowired
    private ResUsaMidDao resUsaMidDao;
    
    @Autowired
    private CpuMemoryMidDao cpuMemoryMidDao;
    
    @Autowired
    private TenantResourceMidDao tenantResourceMidDao;
    
    /**
     * 
     * 加载列表数据并查询
     * 
     * @param searchdisTenant 封装的查询类
     * @param start 起始页
     * @param length 每页数据
     * @param draw ""
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
     * 
     * 查询的语句
     * 
     * @param searchdisTenant 封装查询类
     * @return  querySpecifi
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
                if(StringUtils.isNotBlank(searchdisTenant.getServiceType())){
                    predicates.add(cb.like(root.<String> get("serviceType"), "%"+searchdisTenant.getServiceType()+"%"));
                }
                if(StringUtils.isNotBlank(searchdisTenant.getTenantName())){
                    predicates.add(cb.like(root.<String> get("tenantName"),"%"+searchdisTenant.getTenantName()+"%"));
                }
                if(searchdisTenant.getTenantLevel() != null){
                    predicates.add(cb.equal(root.<Integer> get("tenantLevel"),searchdisTenant.getTenantLevel() ));
                }
                if(StringUtils.isNotBlank(searchdisTenant.getTenantBoss())){
                    predicates.add(cb.like(root.<String> get("tenantBoss"),"%"+searchdisTenant.getTenantBoss()+"%"));
                }
                if(StringUtils.isNotBlank(searchdisTenant.getTenantTel())){
                    predicates.add(cb.like(root.<String> get("tenantTel"), "%"+searchdisTenant.getTenantTel()+"%"));
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
     * 添加excel内容
     * 
     * @param list 数据列表
     * @return  dataset
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
            String storage = distenant.getStorage();
            String storageUsage =distenant.getStorageUsage();
            String storageUsageRate = distenant.getStorageUsageRate() ==  null  ? " ": Double.toString(distenant.getStorageUsageRate());
            String cpuNum = distenant.getCpuNum();
            String cpuMax = distenant.getCpuMax() == null ? " " : String.valueOf(distenant.getCpuMax());
            String cpuAvg = distenant.getCpuAvg() == null ? " " :String.valueOf(distenant.getCpuAvg()) ;
            String memorySize = distenant.getMemorySize();
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
            resourceType = distenant.getResourceType();
            //遍历集合，处理数据
            String[] service = {Integer.toString(i+1), serviceType, tenantName, tenantLevel, tenantBoss, tenantTel,
                resourceType, fileCount, storage,storageUsage,storageUsageRate, cpuNum, cpuMax, cpuAvg, memorySize, memoryMax, memoryAvg,
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
            distenantDao.flush();
            map.put("status", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "400");
        }
        return JSON.toJSONString(map);

    }

    /**
     * 
     * 根据id验证是否可以删除
     * 
     * @param tdId 租户序号
     * @return  返回boolean值
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
//        DisTenantEntity dst = distenantDao.findOne(tdId);
       distenantDao.delete(tdId);
        distenantDao.flush();        
    }

    /**
     * 
     * 加载需要编辑的内容
     * 
     * @param tdId 租户序号
     * @return ""
     * @see
     */
    public DisTenantEntity update(long tdId) {
        return distenantDao.findOne(tdId);
    }

    /**
     * 
     * 对编辑的内容进行保存
     * 
     * @param ditenantEntity 已划配租户实体类
     * @return  ""
     * @see
     */
    public String updateP(DisTenantEntity ditenantEntity) {
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            distenantDao.save(ditenantEntity);
            distenantDao.flush();
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 400);
        }
        return JSON.toJSONString(map);
    }
    
    @Override
    public void getExcelNew(List<DisTenantEntity> list, HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String fileName = "已划配情况导出.xlsx";
        String sheetName = "租户退租情况";
        String[] headers = {"序号", "服务类型", "租户名", "租户级别", "租户负责人", "租户负责人电话", "资源类型", "文件数", "存储",
            "存储使用量",  "存储使用占比", "cpu核数", "cpu最大数", "cpu平均数", "内存大小", "内存最大值",
            "内存平均值", "申请时间", "变更时间", "开放时间"};
        SortList<DisTenantEntity> asort = new SortList<DisTenantEntity>();
        asort.Sort(list, "getTenantName", "desc");
        int index = 1;
        List<List<String[]>> dtEntityLists = new ArrayList<List<String[]>>();
        Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
        for(DisTenantEntity dEntity : list){
            String serviceType = dEntity.getServiceType();
            String tenantName = dEntity.getTenantName();
            String tenantLevel =dEntity.getTenantLevel()== null ? " " :String.valueOf(dEntity.getTenantLevel()) ;
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
            String askDate = dEntity.getAskDate() == null ? " ": String.valueOf(dEntity.getAskDate());
            String changeDate = dEntity.getChangeDate() == null ? " " : String.valueOf(dEntity.getChangeDate());
            String openDate = dEntity.getOpenDate() == null ? " ":String.valueOf(dEntity.getOpenDate());
            switch (dEntity.getTenantLevel()) {
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
        XSSFWorkbook workbook = PoiNewUtil.createWorkBook(sheetName, headers ,dtEntityLists, mergeClom );
        
        
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
     * 
     * Description: <br>
     * 请求分析OrcalAndFtp表
     * @param excelFile 
     * @throws IOException 
     * @see
     */
    public void analyseOrcalAndFtp(MultipartFile excelFile) throws IOException{
        POIUtil.checkFile(excelFile);
        Workbook workbook = POIUtil.getWorkBook(excelFile);
        List<ResourceUsageMidEntity> orcalData = excelAnalyseService.analyseOrcelExcel(workbook);
        List<ResourceUsageMidEntity> ftpData = excelAnalyseService.analyseFtpExcel(workbook);
        excelAnalyseService.orcalToDb(orcalData);
        excelAnalyseService.ftpToDb(ftpData);
    }
    
    /**
     * 
     * Description: <br>
     * 请求分析Hbase的txt
     * @param txtFile 
     * @throws IOException 
     * @see
     */
    public void ananlyseHbase(MultipartFile txtFile) throws IOException{
        InputStream is = txtFile.getInputStream();
        List<ResourceUsageMidEntity> hbaseData = excelAnalyseService.analyseHbaseText(is);
        excelAnalyseService.hbaseToDb(hbaseData);
    }
    
    /**
     * 
     * Description: <br>
     * 请求分析websever的excel
     * @param excelFile 
     * @throws IOException 
     * @see
     */
    public void analyseWebSever(MultipartFile excelFile) throws IOException{
        POIUtil.checkFile(excelFile);
        Workbook workbook = POIUtil.getWorkBook(excelFile);
        List<ResourceUsageMidEntity> webSeverData = excelAnalyseService.analyseWebSeverExcel(workbook);
        excelAnalyseService.orcalToDb(webSeverData);
    }
    
    /**
     * 
     * Description: <br>
     * 请求分析yarn的excel
     * @param excelFile 
     * @throws IOException 
     * @see
     */
    public void analyseYarn(MultipartFile excelFile) throws IOException{
        POIUtil.checkFile(excelFile);
        Workbook workbook = POIUtil.getWorkBook(excelFile);
        List<CpuMemoryMidEntity> orcalData = excelAnalyseService.analyseYarnExcel(workbook);
        excelAnalyseService.yarnToDb(orcalData);
    }
    
    /**
     * 获取中间表数据到tioa_tenant_distribute_show 表中
     */
    @Override
    public void getMidDataToTtd() {
        Map<String, Map<String, Object>> typeMap = new HashMap<String, Map<String, Object>>();
        Map<String, CpuMemoryMidEntity> cmMap = new HashMap<String, CpuMemoryMidEntity>();
        List<ResourceUsageMidEntity> resourceUsageMidEntities = resUsaMidDao.findAll();
        List<CpuMemoryMidEntity> cpuMemoryMidEntities = cpuMemoryMidDao.findAll();
//        List<TenantResourceMidEntity> tenantResourceMidEntities =tenantResourceMidDao.findAll();
        List<DisTenantEntity> disTenantEntities = distenantDao.fixPartOfDisTenant();
        List<DisTenantEntity> disTenantEntitiesFix = new ArrayList<DisTenantEntity>();
        for(ResourceUsageMidEntity usageMidEntity : resourceUsageMidEntities){
            if(typeMap.containsKey(usageMidEntity.getType())){
                if(typeMap.get(usageMidEntity.getType()).containsKey(usageMidEntity.getKeyword())){
                    continue;
                }else{
                    typeMap.get(usageMidEntity.getType()).put(usageMidEntity.getKeyword(), usageMidEntity.getStorageUsage());
                }
            }else{
                Map<String, Object> keyMap = new HashMap<String, Object>();
                keyMap.put(usageMidEntity.getKeyword(), usageMidEntity.getStorageUsage());
                typeMap.put(usageMidEntity.getType(), keyMap);
            }
        }

        for(CpuMemoryMidEntity cmEntity : cpuMemoryMidEntities){
            if(cmMap.containsKey(cmEntity.getSequenceName())){
                continue;
            }else{
                cmMap.put(cmEntity.getSequenceName(), cmEntity);
            }
        }
        
        for(DisTenantEntity disEntity  : disTenantEntities){
            Map<String, Object> webSeverMap = typeMap.get("web服务器");
            Map<String, Object> orcalMap = typeMap.get("orcal");
            Map<String, Object> ftpMap = typeMap.get("ftp");
            Map<String, Object> hbaseMap = typeMap.get("hbase");
            String[] ips =  disEntity.getIpAddr().split(",");
            double usageTemp = 0;
            
            //组装web服务器的使用量
            if(disEntity.getResourceType().equals("web服务器") & ips.length >  0){
                for(String ip : ips){
                    if(webSeverMap.containsKey(ip)){
                        usageTemp += Double.valueOf(webSeverMap.get(ip).toString());
                    }
                    
                }
                disEntity.setResourceType("web服务器");
                disEntity.setStorageUsage(String.valueOf(usageTemp));
                //disEntity.setStorageUsageRate();
            }
            
            //组装orcal的使用量
            if(disEntity.getResourceType().equals("orcal") & orcalMap.containsKey(disEntity.getServiceName())){
                String usageStr = orcalMap.get(disEntity.getServiceName()).toString();
                disEntity.setStorageUsage(usageStr);
            }
            
            //组装ftp的使用量
            if(disEntity.getResourceType().equals("ftp") & ftpMap.containsKey(disEntity.getPath())){
                String usageStr = ftpMap.get(disEntity.getPath()).toString();
                disEntity.setStorageUsage(usageStr);
            }
            
            //组黄hbase的使用量
            if(disEntity.getResourceType().equals("hbase") & hbaseMap.containsKey(disEntity.getPath())){
                String usageStr = hbaseMap.get(disEntity.getPath()).toString();
                disEntity.setStorageUsage(usageStr);
            }
            
            //组装cpu和内存的最大值和平均值
            if("hive,spark".contains(disEntity.getResourceType()) & cmMap.containsKey(cmMap.get(disEntity.getSequenceName()))){
                CpuMemoryMidEntity cEntity = new CpuMemoryMidEntity();
                cEntity = cmMap.get(disEntity.getSequenceName());
                disEntity.setCpuAvg(Integer.valueOf(cEntity.getCpuAvg()));
                disEntity.setCpuMax(Integer.valueOf(cEntity.getCpuMax()));
                disEntity.setMemoryAvg(Integer.valueOf(cEntity.getMemoryAvg()));
                disEntity.setMemoryMax(Integer.valueOf(cEntity.getMemoryMax()));
            }
            disTenantEntitiesFix.add(disEntity);
        }
//        distenantDao.save(disTenantEntitiesFix);
    }
}
