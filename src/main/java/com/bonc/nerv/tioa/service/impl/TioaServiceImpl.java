/*
 * 文件名：TioaServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年7月31日
 */

package com.bonc.nerv.tioa.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.dao.TioaDao;
import com.bonc.nerv.tioa.service.TioaService;
import com.bonc.nerv.tioa.util.SavaToExcelUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tioa服务实现类，测试用
 * @author yuanpeng
 * @version 2017年7月31日
 * @see TioaServiceImpl
 * @since
 */
@Service
public class TioaServiceImpl implements TioaService {
    @Autowired
    private TioaDao tioaDao;
    /**
     * 
     */
    public void testOne() {
        // TODO Auto-generated method stub
    }

    /**
     * 
     */
    public void testTwo() {
        // TODO Auto-generated method stub

    }

    @Override
    public void savaToFile() {
        
        List<String> title = new ArrayList<String>();
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
        
        
        List<String> columnNames = new ArrayList<String>();
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
            List<List<Object>> list = tioaDao.getAllTioa();
            SavaToExcelUtils.writeExcel("E:\\temp", "qqq","租户计费情况" ,columnNames ,title ,list );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
