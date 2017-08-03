/*
 * 文件名：TioaTenantChargingShow.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tioa_tenant_charging_show")
public class TioaTenantChargingShow{
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tc_id")
    private Long tcId;
    
    /**
     * 租户名
     */
    @Column(name = "tenant_name")
    private String tenantName;
    
    /**
     * 服务类型
     */
    @Column(name = "service_type")
     private Integer serviceType;
    
    /**
     * 租户分类
     */
   @Column(name = "tenant_type")
    private Integer tenantType;
    
    
   
    
    /**
     * 租户级别
     */
   /* @Column(name = "tenant_level")
    private Integer tenantLevel;
     
    *//**
     * 租户负责人
     *//*
    @Column(name = "tenant_boss")
    private String tenantBoss;
    
    *//**
     * 租户负责人电话
     *//*
    @Column(name = "tenant_tel")
    private String tenantTel;
    
    *//**
     * 平台接口人
     *//*
    @Column(name = "tenant_interface")
    private String tenantInterface;*/
    
    
    
    /**
     * 资源具备时间
     */
    @Column(name = "resource_time")
    private String resourceTime;
    
    /**
     * 统一和4A
     */
    @Column(name = "uniplatform_4a_time")
    private String uniplatform4aTime;
    
    /**
     * 数据实际具备时间
     */
    @Column(name = "havedate_time")
    private String havedateTime;
    
    /**
     * 入住时长
     */
    @Column(name = "reside_duration")
    private String resideDuration;
    
    /**
     * 月租费用
     */
    @Column(name = "monthly_fee")
    private Integer monthlyFee;
    
    /**
     * 数据报价
     */
    @Column(name = "data_fee")
    private Float dataFee;
    
    /**
     * 起租日期
     */
    @Column(name = "begin_rent_date")
    private String beginRentDate;
    
    /**
     * 试用期
     */
    @Column(name = "taste_duration")
    private String tasteDuration;
    
    /**
     * 计费日期
     */
    @Column(name = "charge_begin_date")
    private String chargeBeginDate;
   
    /**
     * 到期日期
     */
    @Column(name = "charge_end_date")
    private String chargeEndDate;
    
    /**
     * 联通引入方
     */
    @Column(name = "introduce_unicom")
    private String introduceUnicom;
    
    /**
     * 引入方管理员
     */
    @Column(name = "introduce_tenant")
    private String introduceTenant;
    
    /**
     * 联系方式
     */
    @Column(name = "contact")
    private String contact;
    
    /**
     * 申请日期
     */
    @Column(name = "ask_date")
    private String askDate;
    
    /**
     * 是否签署合同
     */
    @Column(name = "sign_contract")
    private Integer signContract;
    
    /**
     * 月租备注
     */
    @Column(name = "monthly_fee_remark")
    private String monthlyFeeRemark;

    /**
     * 退租时间
     */
    @Column(name = "end_rent_date")
    private String endRentDate;
    
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    
    public Long getTcId(){
        return tcId;
    }

    public void setTcId(Long tcId){
        this.tcId = tcId;
    }

    public String getTenantName(){
        return tenantName;
    }

    public void setTenantName(String tenantName){
        this.tenantName = tenantName;
    }

    public Integer getTenantType(){
        return tenantType;
    }

    public void setTenantType(Integer tenantType){
        this.tenantType = tenantType;
    }

    public Integer getServiceType(){
        return serviceType;
    }

    public void setServiceType(Integer serviceType){
        this.serviceType = serviceType;
    }

   

    public String getResourceTime(){
        return resourceTime;
    }

    public void setResourceTime(String resourceTime){
        this.resourceTime = resourceTime;
    }

    public String getUniplatform4aTime(){
        return uniplatform4aTime;
    }

    public void setUniplatform4aTime(String uniplatform4aTime){
        this.uniplatform4aTime = uniplatform4aTime;
    }

    public String getHavedateTime(){
        return havedateTime;
    }

    public void setHavedateTime(String havedateTime){
        this.havedateTime = havedateTime;
    }

    public String getResideDuration(){
        return resideDuration;
    }

    public void setResideDuration(String resideDuration){
        this.resideDuration = resideDuration;
    }

    public Integer getMonthlyFee(){
        return monthlyFee;
    }

    public void setMonthlyFee(Integer monthlyFee){
        this.monthlyFee = monthlyFee;
    }

    public Float getDataFee(){
        return dataFee;
    }

    public void setDataFee(Float dataFee){
        this.dataFee = dataFee;
    }

    public String getBeginRentDate(){
        return beginRentDate;
    }

    public void setBeginRentDate(String beginRentDate){
        this.beginRentDate = beginRentDate;
    }

    public String getTasteDuration(){
        return tasteDuration;
    }

    public void setTasteDuration(String tasteDuration){
        this.tasteDuration = tasteDuration;
    }

    public String getChargeBeginDate(){
        return chargeBeginDate;
    }

    public void setChargeBeginDate(String chargeBeginDate){
        this.chargeBeginDate = chargeBeginDate;
    }

    public String getChargeEndDate(){
        return chargeEndDate;
    }

    public void setChargeEndDate(String chargeEndDate){
        this.chargeEndDate = chargeEndDate;
    }

    public String getIntroduceUnicom(){
        return introduceUnicom;
    }

    public void setIntroduceUnicom(String introduceUnicom){
        this.introduceUnicom = introduceUnicom;
    }

    public String getIntroduceTenant(){
        return introduceTenant;
    }

    public void setIntroduceTenant(String introduceTenant){
        this.introduceTenant = introduceTenant;
    }

    public String getContact(){
        return contact;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    public String getAskDate(){
        return askDate;
    }

    public void setAskDate(String askDate){
        this.askDate = askDate;
    }

    public Integer getSignContract(){
        return signContract;
    }

    public void setSignContract(Integer signContract){
        this.signContract = signContract;
    }

    public String getMonthlyFeeRemark(){
        return monthlyFeeRemark;
    }

    public void setMonthlyFeeRemark(String monthlyFeeRemark){
        this.monthlyFeeRemark = monthlyFeeRemark;
    }

    public String getEndRentDate(){
        return endRentDate;
    }

    public void setEndRentDate(String endRentDate){
        this.endRentDate = endRentDate;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }
    
    
    

}
