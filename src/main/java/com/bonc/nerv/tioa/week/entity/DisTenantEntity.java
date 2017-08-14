/*
 * 文件名：UserEntity.java 
 * 版权：Copyright by www.bonc.com.cn 
 * 描述： 
 * 修改人：zhangwen 
 * 修改时间：2017年7月27日
 * 跟踪单号：
 * 修改单号： 修改内容：
 */

package com.bonc.nerv.tioa.week.entity;


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
    @Column(name = "td_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tdId;

    /*
     * 服务类型
     */
    @Basic
    @Column(name = "service_type")
    private String serviceType;

    /*
     * 租户名
     */
    @Basic
    @Column(name = "tenant_name")
    private String tenantName;

    /*
     * 租户级别
     */
    @Basic
    @Column(name = "tenant_level")
    private Integer tenantLevel;

    /*
     * 租户负责人
     */
    @Basic
    @Column(name = "tenant_boss")
    private String tenantBoss;

    /*
     * 租户负责人电话
     */
    @Basic
    @Column(name = "tenant_tel")
    private String tenantTel;

    /*
     * 资源类型
     */
    @Basic
    @Column(name = "resource_type")
    private String resourceType;

    /*
     * 文件数
     */
    @Basic
    @Column(name = "file_count")
    private Integer fileCount;

    /*
     * 存储
     */
    @Basic
    @Column(name = "storage")
    private String storage;

    /*
     * 存储使用量
     */
    @Basic
    @Column(name = "storage_usage")
    private String storageUsage;

    /*
     * 存储使用量占比
     */
    @Basic
    @Column(name = "storage_usage_rate")
    private Double storageUsageRate;

    /*
     * cpu 核数
     */
    @Basic
    @Column(name = "cpu_num")
    private String cpuNum;

    /*
     * cpu 最大数
     */
    @Basic
    @Column(name = "cpu_max")
    private Integer cpuMax;

    /*
     * cpu 平均数
     */
    @Basic
    @Column(name = "cpu_avg")
    private Integer cpuAvg;

    /*
     * 内存大小
     */
    @Basic
    @Column(name = "memory_size")
    private String memorySize;

    /*
     * 内存最大值
     */
    @Basic
    @Column(name = "memory_max")
    private Integer memoryMax;

    /*
     * 内存平均值
     */
    @Basic
    @Column(name = "memory_avg")
    private Integer memoryAvg;

    /*
     * 申请日期
     */
    @Basic
    @Column(name = "ask_date")
    private String askDate;

    /*
     * 变更日期
     */
    @Basic
    @Column(name = "change_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String changeDate;

    /*
     * 开放日期
     */
    @Basic
    @Column(name = "open_date")
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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getStorageUsage() {
        return storageUsage;
    }

    public void setStorageUsage(String storageUsage) {
        this.storageUsage = storageUsage;
    }

    public Double getStorageUsageRate() {
        return storageUsageRate;
    }

    public void setStorageUsageRate(Double storageUsageRate) {
        this.storageUsageRate = storageUsageRate;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
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

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
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
