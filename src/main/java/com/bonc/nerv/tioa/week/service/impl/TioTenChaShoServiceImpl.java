/*
 * 文件名：TioaTenantChargingShowServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：leijin
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.week.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.week.dao.TioTenChaSho_2Dao;
import com.bonc.nerv.tioa.week.dao.TioaTenantChargingShowDao;
import com.bonc.nerv.tioa.week.entity.TioaTenantChargingShow;
import com.bonc.nerv.tioa.week.service.TioTenChaShoService;
import com.bonc.nerv.tioa.week.util.POIUtil;
import com.bonc.nerv.tioa.week.util.SavaToExcelUtils;

/**
 * 
 * @author leijin
 * @version 2017年8月3日
 * @see TioTenChaShoServiceImpl
 * @since
 */
@Service("tioaTenantChargingShowService")
public class TioTenChaShoServiceImpl implements TioTenChaShoService{

    /**
     * 注入到tioaTenantChargingShowDao
     */
    @Autowired
    private TioaTenantChargingShowDao tioaTenantChargingShowDao;
    
    /**
     * 注入到tioTenChaSho_2Dao
     */
    @Autowired
    private TioTenChaSho_2Dao tioTenChaSho_2Dao;
    
    
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
         
            TioaTenantChargingShow tioaTenantChargingShow = new TioaTenantChargingShow();
            //租户
            if(list.get(i)[0] == null || 0 == list.get(i)[0].length()){
                continue;
            }
            tioaTenantChargingShow.setTenantName(list.get(i)[0]);
            //服务类型
            tioaTenantChargingShow.setServiceType(getServiceType(list.get(i)[1]));
            //租户分类
            tioaTenantChargingShow.setTenantType(getTenantType(list.get(i)[2]));
            //资源具备时间
            tioaTenantChargingShow.setResourceTime(list.get(i)[3]);
            //统一和4A
            tioaTenantChargingShow.setUniplatform4aTime(list.get(i)[4]);    
            //数据实际具备时间
            tioaTenantChargingShow.setHavedateTime(list.get(i)[5]);
            //入住时长
            tioaTenantChargingShow.setResideDuration(list.get(i)[6]);
            //月租费用
            tioaTenantChargingShow.setMonthlyFee(getInteger(list.get(i)[7]));
            //数据报价
            tioaTenantChargingShow.setDataFee(getFloat(list.get(i)[8]));
            //起租日期
            tioaTenantChargingShow.setBeginRentDate(list.get(i)[9]);
            //试用期
            tioaTenantChargingShow.setTasteDuration(list.get(i)[10]);
            //计费日期
            tioaTenantChargingShow.setChargeBeginDate(list.get(i)[11]);
            //到期日期
            tioaTenantChargingShow.setChargeEndDate(list.get(i)[12]);
            //联通引入方
            tioaTenantChargingShow.setIntroduceUnicom(list.get(i)[13]);
            //引入方管理员
            tioaTenantChargingShow.setIntroduceTenant(list.get(i)[14]);
            //联系方式
            tioaTenantChargingShow.setContact(list.get(i)[15]);
            //申请日期
            tioaTenantChargingShow.setAskDate(list.get(i)[16]);
            //是否签署合同
            tioaTenantChargingShow.setSignContract(getSignContract(list.get(i)[17]));
            //月租备注
            tioaTenantChargingShow.setMonthlyFeeRemark(list.get(i)[18]);
            //退租时间
            tioaTenantChargingShow.setEndRentDate(list.get(i)[19]);
            //备注
            tioaTenantChargingShow.setRemark(list.get(i)[20]);
           
            tioaTenantChargingShowDao.save(tioaTenantChargingShow);
        }
   
    }
    
  
    /**
     * 根据字符返回服务类型，TENANT_INTERNAL表示内部，TENANT_EXTERNAL表示外部
     * @param serviceType   
     * @return int
     * @see
     */
    public Integer getServiceType(String serviceType){
        if (serviceType.equals("内部")) {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_INTERNAL;
        } else {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_EXTERNAL;
        }
    }
    
    /**
     * 根据字符返回租户分类，TENANT_RECENT表示近期租户，TENANT_HISTORICAL表示历史租户
     * @param tenantType   
     * @return int
     * @see
     */
    public Integer getTenantType(String tenantType){
        if (tenantType.equals("近期租户")) {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_RECENT;
        } else {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_HISTORICAL;
        }
    }
    
    /**
     * 是否签署合同
     * @param signContract   
     * @return int
     * @see
     */
    public Integer getSignContract(String signContract){
        if (signContract.equals("是")) {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_CONTRACT;
        } else {
            return com.bonc.nerv.tioa.week.constant.TioaConstant.TENANT_NO_CONTRACT;
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
    
    /**
     * 字符串转浮点型 
     * @param str  
     * @return float
     * @see
     */
    public Float getFloat(String str){
        if (str != null && !str.equals("")) {
            return Float.parseFloat(str);
        } else {
            return null;
        }
    }
    
    
    /**
     * 保存新增数据
     * @param tioaTenantChargingShow   
     * @return 
     * @see
     */
    public void save(TioaTenantChargingShow tioaTenantChargingShow){
        tioaTenantChargingShowDao.save(tioaTenantChargingShow);
    }
    
    /**
     * 根据id删除数据
     * @param id  
     * @return 
     * @see
     */
    public void deleteById(Long id){
        tioaTenantChargingShowDao.delete(id);
    }
    
    /**
     * Description: 将数据保存到excel文件
     * @param request 请求的request对象
     * @param response  响应的response对象
     * @see 
     */
    
    @Override
    public void savaToFile(HttpServletRequest request,
                           HttpServletResponse response) {
        
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
            List<List<Object>> list = tioTenChaSho_2Dao.getAllTioa();
            SavaToExcelUtils.writeExcel( response, "租户计费情况.xlsx","租户计费情况" ,columnNames ,title ,list );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 定时向接口人发送邮件（调用消息渠道的接口）
     */
    @Override
    public void sendEmailToInterface() {
        
    }
    
}
