/*
 * 文件名：TenretiredEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年7月28日
 */

package com.bonc.nerv.tioa.week.entity;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * 
 * 已退租户实体类
 * @author ymm
 * @version 2017年7月31日
 * @see TenretiredEntity
 * @since
 */

@Entity
@Table(name="tioa_tenant_leave_show")
public class TenretiredEntity {
    
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
     private long tlId;    
    /**
     * 租户名
     */
     private String tenantName;
     
    
    /**
     * 服务类型
     */
    private String serviceType;
    
    /**
     * 租户级别
     */
    private Integer tenantLevel;
    
    /**
     * 租户负责人
     */
    private String tenantBoss;
    
    /**
     * 联系电话
     */
    private String tenantTel;
    
    /**
     * 资源类型
     */
    private String resourceType;
    
    /**
     * 访问IP
     */
    private String askIp;
    
    /**
     * 台数
     */
    private Integer hostNum;
    
    /**
     * 存储使用量
     */
    private String storage;
    
    /**
     * 计算资源
     */
    private Double computingResourceRate;
    
    /**
     * 机房
     */
    private String computeRoom;
    
    /**
     * 统一平台个数
     */
    private Integer uniplatformNum;
    
    /**
     * 4A个数
     */
    private Integer numOf4a;

    /**
     * 需求
     */
    private String demand;
    
    /**
     * 服务名
     */
    private String serviceName;
    
    /**
     * 对列名
     */
    private String sequenceName;
    
    /**
     * 申请时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date askDate;
    
    /**
     * 开放时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date openDate;
    
    /**
     * 变更时间
     */
    private String changeDate;
    
    /**
     * 退租时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date endRentDate;
    
    /**
     * 平台接口人
     */
    private String tenantInterface;
    
    /**
     * 备注
     */
    private String remark;
    
   
    public long getTlId() {
        return tlId;
    }

    public void setTlId(long tlId) {
        this.tlId = tlId;
    }
    
    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
    public Integer getTenantLevel() {
        return tenantLevel;
    }

    public void setTenantLevel(Integer tenantLevel) {
        this.tenantLevel = tenantLevel;
    }
    
    public String getTenantBoss() {
        return tenantBoss;
    }

    public void setTenantBoss(String tenantBoss) {
        this.tenantBoss = tenantBoss;
    }
    
    @Basic
    @Column(name="tenantTel",nullable=true)
    public String getTenantTel() {
        return tenantTel;
    }

    public void setTenantTel(String tenantTel) {
        this.tenantTel = tenantTel;
    }
    
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    public String getAskIp() {
        return askIp;
    }

    public void setAskIp(String askIp) {
        this.askIp = askIp;
    }
    
    public Integer getHostNum() {
        return hostNum;
    }

    public void setHostNum(Integer hostNum) {
        this.hostNum = hostNum;
    }
    
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
    
    public Double getComputingResourceRate() {
        return computingResourceRate;
    }

    public void setComputingResourceRate(Double computingResourceRate) {
        this.computingResourceRate = computingResourceRate;
    }
    
    public String getComputeRoom() {
        return computeRoom;
    }

    public void setComputeRoom(String computeRoom) {
        this.computeRoom = computeRoom;
    }
    
    public Integer getUniplatformNum() {
        return uniplatformNum;
    }

    public void setUniplatformNum(Integer uniplatformNum) {
        this.uniplatformNum = uniplatformNum;
    }
    
    public Integer getNumOf4a() {
        return numOf4a;
    }

    public void setNumOf4a(Integer numOf4a) {
        this.numOf4a = numOf4a;
    }
    
    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }
    
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }
    
    public Date getAskDate() {
        return askDate;
    }

    public void setAskDate(Date askDate) {
        this.askDate = askDate;
    }
    
    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    
    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }
    
    public Date getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(Date endRentDate) {
        this.endRentDate = endRentDate;
    }
    
    public String getTenantInterface() {
        return tenantInterface;
    }

    public void setTenantInterface(String tenantInterface) {
        this.tenantInterface = tenantInterface;
    }
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Override
    public String toString() {
        return "TenretiredEntity [tlId=" + tlId + ", serviceType=" + serviceType
               + ", tenantName=" + tenantName + ", tenantLevel=" + tenantLevel + ", tenantBoss="
               + tenantBoss + ", tenantTel=" + tenantTel +", resourceType=" + resourceType +", askIp=" + askIp 
               +", hostNum=" + hostNum +", storage=" + storage+", computingResourceRate=" + computingResourceRate
               +", computeRoom=" + computeRoom +", uniplatformNum=" + uniplatformNum 
               +", numOf4a=" + numOf4a +", demand=" + demand +", serviceName=" + serviceName 
               +", sequenceName=" +sequenceName+", askDate=" + askDate +", openDate=" + openDate
               +", changeDate=" + changeDate+", endRentDate=" + endRentDate+", tenantInterface=" +tenantInterface
               +", remark=" + remark +"]";
    }

    
    
    
}
