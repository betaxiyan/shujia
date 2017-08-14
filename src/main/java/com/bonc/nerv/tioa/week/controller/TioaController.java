package com.bonc.nerv.tioa.week.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonc.nerv.tioa.week.dao.TenretiredDao;
import com.bonc.nerv.tioa.week.entity.TenretiredEntity;
import com.bonc.nerv.tioa.week.util.PoiNewUtil;
import com.bonc.nerv.tioa.week.util.SortList;



/**
 * 
 * 调用外部restful接口的服务类
 * @author yuanpeng
 * @version 2017年7月31日
 * @see TioaController
 * @since
 */
@Controller
public class TioaController {    
    @Autowired
    private TenretiredDao tenretiredDao;
    /**
     * 
     * Description: <br>
     * 进入实施自动化展示页面
     * @return 
     * @see
     */
    @RequestMapping("/view")
    public String inter(){
        return "view/data_view";
    }
    
    @RequestMapping("test")
    public void test() throws FileNotFoundException, IOException{
        String sheetName = "租户退租情况";
        String[] headers = {"序号","服务类型","租户","租户级别","租户负责人","联系电话","资源类型","访问IP","主机数量",
            "存储使用量","存储使用量单位","计算资源","机房","统一平台数量","4A数量","需求","服务名","队列名","申请日期","开放日期",
            "变更时间","退租时间","平台接口人","备注"};
        TenretiredEntity ten1 = new TenretiredEntity();
        ten1.setTenantName("test");
        ten1.setTenantLevel(1);
        TenretiredEntity ten2 = new TenretiredEntity();
        ten2.setTenantName("test");
        ten2.setTenantLevel(1);
        TenretiredEntity ten3 = new TenretiredEntity();
        ten3.setTenantName("test");
        ten3.setTenantLevel(1);
        TenretiredEntity ten4 = new TenretiredEntity();
        ten4.setTenantName("test");
        ten4.setTenantLevel(1);
        List<TenretiredEntity> tenList = new ArrayList<TenretiredEntity>();//已退租户
        //tenList = tenretiredDao.findAll();
        tenList.add(ten1);
        tenList.add(ten2);
        tenList.add(ten3);
        tenList.add(ten4);
        
        List<TenretiredEntity> tenList2 = new ArrayList<TenretiredEntity>();//已退租户
        tenList2.add(ten1);
        tenList2.add(ten2);
        tenList2.add(ten3);
        tenList2.add(ten4);
        
        List<List<TenretiredEntity>> ttEntityLists = new ArrayList<List<TenretiredEntity>>();
        ttEntityLists.add(tenList);
        ttEntityLists.add(tenList2);
        Integer[] mergeClom = {0 ,1};
 //       XSSFWorkbook workbook = PoiNewUtil.createWorkBook(sheetName, headers ,ttEntityLists, mergeClom );
        FileOutputStream out = new FileOutputStream("D:/newExcel.xlsx");
 //       workbook.write(out);
        out.close();
        
    }
   
    @RequestMapping("test2")
    public void test2() throws FileNotFoundException, IOException{
        String sheetName = "租户退租情况";
        String[] headers = {"序号","服务类型","租户","租户级别","租户负责人","联系电话","资源类型","访问IP","主机数量",
            "存储使用量","存储使用量单位","计算资源","机房","统一平台数量","4A数量","需求","服务名","队列名","申请日期","开放日期",
            "变更时间","退租时间","平台接口人","备注"};
        List<TenretiredEntity> atenList = tenretiredDao.findAll();
        SortList<TenretiredEntity> asort = new SortList<TenretiredEntity>();
        asort.Sort(atenList, "getTenantName", "desc");
        //String tenantName = 
        
        int index = 1;
        List<List<String[]>> ttEntityLists = new ArrayList<List<String[]>>();
        Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
        for(TenretiredEntity teEntity : atenList){
            String[] tenStr ={String.valueOf(index),
                teEntity.getServiceType(),
                teEntity.getTenantName(),
                String.valueOf(teEntity.getTenantLevel()),
                teEntity.getTenantBoss(),
                teEntity.getTenantTel(),
                String.valueOf(teEntity.getResourceType()),
                teEntity.getAskIp(),
                String.valueOf(teEntity.getHostNum()), //8
                teEntity.getStorage(),
                String.valueOf(teEntity.getComputingResourceRate()),
                teEntity.getComputeRoom(),
                String.valueOf(teEntity.getUniplatformNum()),
                String.valueOf(teEntity.getNumOf4a()),
                teEntity.getDemand(),
                teEntity.getServiceName(),
                teEntity.getSequenceName(),
                String.valueOf(teEntity.getAskDate()),
                String.valueOf(teEntity.getOpenDate()),
                String.valueOf(teEntity.getChangeDate()),
                String.valueOf(teEntity.getTenantLevel()),
                teEntity.getTenantInterface(),
                teEntity.getRemark()
            };
            String tenantName = teEntity.getTenantName();
            if(map.containsKey(tenantName)){
                map.get(tenantName).add(tenStr);
            } else{
                List<String[]> newList = new ArrayList<String[]>();
                newList.add(tenStr);
                ttEntityLists.add(newList);
                map.put(tenantName, newList);
                index++;
            }
            
        }
        
        Integer[] mergeClom = {0 , 1, 2, 3, 4, 5, 12, 13, 14, 21};
        XSSFWorkbook workbook = PoiNewUtil.createWorkBook(sheetName, headers ,ttEntityLists, mergeClom );
        FileOutputStream out = new FileOutputStream("D:/newExcel.xlsx");
        workbook.write(out);
        out.close();
        
    }
}