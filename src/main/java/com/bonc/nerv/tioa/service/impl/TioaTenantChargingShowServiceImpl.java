/*
 * 文件名：TioaTenantChargingShowServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.nerv.tioa.constant.TioaConstant;
import com.bonc.nerv.tioa.dao.TioaTenantChargingShowDao;
import com.bonc.nerv.tioa.entity.TioaTenantChargingShow;
import com.bonc.nerv.tioa.service.TioaTenantChargingShowService;
import com.bonc.nerv.tioa.util.POIUtil;

@Service("tioaTenantChargingShowService")
public class TioaTenantChargingShowServiceImpl implements TioaTenantChargingShowService{

    @Autowired
    TioaTenantChargingShowDao tioaTenantChargingShowDao;
    
    
    @Override
    public void saveExcel(MultipartFile excelFile) throws ParseException {
        System.out.println("执行到service");
        List<String[]> list = null;       
        try {
            list = POIUtil.readExcel(excelFile);
            System.out.println(list.size());       
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TioaConstant tioaConstant = new TioaConstant();
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
     * 根据字符返回服务类型，10表示内部，20表示外部
     * @param serviceType
     * @return 
     * @see
     */
    public Integer getServiceType(String serviceType){
        if (serviceType.equals("内部")) {
            return com.bonc.nerv.tioa.constant.TioaConstant.TENANT_INTERNAL
;
        } else {
            return com.bonc.nerv.tioa.constant.TioaConstant.TENANT_EXTERNAL;
        }
    }
    
    /**
     * 根据字符返回租户分类，10表示近期租户，20表示历史租户
     * @param tenantType
     * @return 
     * @see
     */
    public Integer getTenantType(String tenantType){
        if (tenantType.equals("近期租户")) {
            return com.bonc.nerv.tioa.constant.TioaConstant.TENANT_RECENT;
        } else {
            return com.bonc.nerv.tioa.constant.TioaConstant.TENANT_HISTORICAL;
        }
    }
    /**
     * 是否签署合同
     * @param signContract
     * @return 
     * @see
     */
    
    public Integer getSignContract(String signContract){
        if (signContract.equals("是")) {
            return com.bonc.nerv.tioa.constant.TioaConstant.TENANT_CONTRACT;
        } else {
            return com.bonc.nerv.tioa.constant.TioaConstant.TENANT_NO_CONTRACT;
        }
    }
    
   
    
    /**
     * 字符串转整形
     * @param str
     * @return 
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
     * @return 
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
    
}
