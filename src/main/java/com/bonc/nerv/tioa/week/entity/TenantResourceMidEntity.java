/*
 * 文件名：TenantResourceMidEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月2日
 */

package com.bonc.nerv.tioa.week.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.alibaba.fastjson.annotation.JSONField;

/**
 *  租户资源中间表实体类
 * @author zhiyong
 * @version 2017年8月2日
 * @see TenantResourceMidEntity
 * @since
 */

@Entity
@Table(name = "tenant_resource_mid")
public class TenantResourceMidEntity {
    
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trm_id")
    private Long trmId;
    
    /**
     * 租户id
     */
    @Column(name = "tenant_id")
    private String tenantId;
    
    /**
     * 租户名
     */
    @Column(name = "tenant_name")
    private String tenantName;
    
    /**
     * 服务类型
     */
    @Column(name = "service_type")
    private String serviceType;
    
    /**
     * 租户账号
     */
    @Column(name = "tenant_account")
    private String tenantAccount;
    
    /**
     * ip地址
     */
    @Column(name = "ip_addr")
    private String ipAddr;
    
    /**
     * 资源类型
     */
    @Column(name = "type_name")
    private String typeName;
    
    /**
     * 存储使用量
     */
    @Column(name = "storage")
    private String storage;
    
    /**
     * cpu 核数
     */
    @Column(name = "cpu_num")
    private String cpuNum;
    
    /**
     * 内存大小
     */
    @Column(name = "memory")
    private String memory;
    
    /**
     * 服务名
     */
    @Column(name = "service_name")
    private String serviceName;
    
    /**
     * 队列名
     */
    @Column(name = "sequence_name")
    private String sequenceName;
    
    /**
     * 路径名
     */
    private String path;
    
    /**
     * 机房
     */
    @Column(name = "compute_room")
    private String computeRoom;
    
    /**
     * 申请日期
     */
    @Column(name = "ask_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date askDate;
    
    /**
     * 开放日期
     */
    @Column(name = "open_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date openDate;
    
    /**
     * 更新时间(原为退租时间)
     */
    @Column(name = "end_rent_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endRentDate;
    
    /**
     * 是否有效
     */
    private String isInvalid;
    
    public Long getTrmId() {
        return trmId;
    }

    public void setTrmId(Long trmId) {
        this.trmId = trmId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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

    public String getTenantAccount() {
        return tenantAccount;
    }

    public void setTenantAccount(String tenantAccount) {
        this.tenantAccount = tenantAccount;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComputeRoom() {
        return computeRoom;
    }

    public void setComputeRoom(String computeRoom) {
        this.computeRoom = computeRoom;
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

    public Date getEndRentData() {
        return endRentDate;
    }

    public void setEndRentData(Date endRentDate) {
        this.endRentDate = endRentDate;
    }
    
    public String getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }

    @Override
    public String toString() {
        return "TenantResourceMidEntity [trmId=" + trmId + ", tenantId=" + tenantId
               + ", tenantName=" + tenantName + ", serviceType=" + serviceType + ", tenantAccount="
               + tenantAccount + ", ipAddr=" + ipAddr + ", typeName=" + typeName + ", storage="
               + storage + ", cpuNum=" + cpuNum + ", memory=" + memory + ", serviceName="
               + serviceName + ", sequenceName=" + sequenceName + ", computeRoom=" + computeRoom
               + ", askDate=" + askDate + ", openDate=" + openDate + ", endRentDate=" + endRentDate
               + ", isInvalid=" + isInvalid + "]";
    }
    
}
