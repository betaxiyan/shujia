/*
 * 文件名：UserEntity.java 
 * 版权：Copyright by www.bonc.com.cn 
 * 描述： 
 * 修改人：zhangwen 
 * 修改时间：2017年7月27日
 * 跟踪单号：
 * 修改单号： 修改内容：
 */

package com.bonc.nerv.tioa.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;


@Entity
@Table(name = "tioa_tenant_distribute_show", schema = "nerv-tioa", catalog = "")
public class DisTenantEntity {

    /*
     * 租户Id
     */
    @Id
    @Column(name = "tdid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tdId;

    /*
     * 服务类型
     */
    @Basic
    @Column(name = "service_type", length = 45)
    private String serviceType;

    /*
     * 租户名
     */
    @Basic
    @Column(name = "tenant_name", length = 45)
    private String tenantName;

    /*
     * 租户级别
     */
    @Basic
    @Column(name = "tenant_level", length = 45)
    private Integer tenantLevel;

    /*
     * 租户负责人
     */
    @Basic
    @Column(name = "tenant_boss", length = 45)
    private String tenantBoss;

    /*
     * 租户负责人电话
     */
    @Basic
    @Column(name = "tenant_tel", length = 45)
    private String tenantTel;

    /*
     * 资源类型
     */
    @Basic
    @Column(name = "resource_type", length = 45)
    private Integer resourceType;

    /*
     * 文件数
     */
    @Basic
    @Column(name = "file_count", length = 45)
    private Integer fileCount;

    /*
     * 存储
     */
    @Basic
    @Column(name = "storage", length = 45)
    private Integer storage;

    /*
     * 存储单位
     */
    @Basic
    @Column(name = "storage_unit", length = 45)
    private String storageUnit;

    /*
     * 存储使用量
     */
    @Basic
    @Column(name = "storage_usage", length = 45)
    private Integer storageUsage;

    /*
     * 存储使用量单位
     */
    @Basic
    @Column(name = "storage_usage_unit", length = 45)
    private String storageUsageUnit;

    /*
     * 存储使用量占比
     */
    @Basic
    @Column(name = "storage_usage_rate", length = 45)
    private double storageUsageRate;

    /*
     * cpu 核数
     */
    @Basic
    @Column(name = "cpu_num", length = 45)
    private Integer cpuNum;

    /*
     * cpu 最大数
     */
    @Basic
    @Column(name = "cpu_max", length = 45)
    private Integer cpuMax;

    /*
     * cpu 平均数
     */
    @Basic
    @Column(name = "cpu_avg", length = 45)
    private Integer cpuAvg;

    /*
     * 内存大小
     */
    @Basic
    @Column(name = "memory_size", length = 45)
    private Integer memorySize;

    /*
     * 内存最大值
     */
    @Basic
    @Column(name = "memory_max", length = 45)
    private Integer memoryMax;

    /*
     * 内存平均值
     */
    @Basic
    @Column(name = "memory_avg", length = 45)
    private Integer memoryAvg;

    /*
     * 申请日期
     */
    @Basic
    @Column(name = "ask_date", length = 45)
    private String askDate;

    /*
     * 变更日期
     */
    @Basic
    @Column(name = "change_date", length = 45)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String changeDate;

    /*
     * 开放日期
     */
    @Basic
    @Column(name = "open_date", length = 45)
    private String openDate;

    public long getTdId() {
        return tdId;
    }

    public void setTdId(long tdId) {
        this.tdId = tdId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
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

    public String getTenantTel() {
        return tenantTel;
    }

    public void setTenantTel(String tenantTel) {
        this.tenantTel = tenantTel;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getStorageUnit() {
        return storageUnit;
    }

    public void setStorageUnit(String storageUnit) {
        this.storageUnit = storageUnit;
    }

    public Integer getStorageUsage() {
        return storageUsage;
    }

    public void setStorageUsage(Integer storageUsage) {
        this.storageUsage = storageUsage;
    }

    public String getStorageUsageUnit() {
        return storageUsageUnit;
    }

    public void setStorageUsageUnit(String storageUsageUnit) {
        this.storageUsageUnit = storageUsageUnit;
    }

    public double getStorageUsageRate() {
        return storageUsageRate;
    }

    public void setStorageUsageRate(double storageUsageRate) {
        this.storageUsageRate = storageUsageRate;
    }

    public Integer getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }

    public Integer getCpuMax() {
        return cpuMax;
    }

    public void setCpuMax(Integer cpuMax) {
        this.cpuMax = cpuMax;
    }

    public Integer getCpuAvg() {
        return cpuAvg;
    }

    public void setCpuAvg(Integer cpuAvg) {
        this.cpuAvg = cpuAvg;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }

    public Integer getMemoryMax() {
        return memoryMax;
    }

    public void setMemoryMax(Integer memoryMax) {
        this.memoryMax = memoryMax;
    }

    public Integer getMemoryAvg() {
        return memoryAvg;
    }

    public void setMemoryAvg(Integer memoryAvg) {
        this.memoryAvg = memoryAvg;
    }

    public String getAskDate() {
        return askDate;
    }

    public void setAskDate(String askDate) {
        this.askDate = askDate;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }


}
