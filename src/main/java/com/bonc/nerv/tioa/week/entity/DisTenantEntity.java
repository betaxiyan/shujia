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

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 已化配租户情况表实体类
 * 注意：有四个在数据库表上没有字段的属性 （用@Transient注释）
 * @author HCN
 * @version 2017年8月24日
 * @see DisTenantEntity
 * @since
 */
@Entity
@Table(name = "tioa_tenant_distribute_show", schema = "nerv-tioa", catalog = "")
public class DisTenantEntity {

    /**
     * 租户Id
     */
    @Id
    @Column(name = "td_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tdId;

    /**
     * 服务类型
     */
    @Basic
    @Column(name = "service_type")
    private String serviceType;

    /**
     * 租户名
     */
    @Basic
    @Column(name = "tenant_name")
    private String tenantName;

    /**
     * 租户级别
     */
    @Basic
    @Column(name = "tenant_level")
    private Integer tenantLevel;

    /**
     * 租户负责人
     */
    @Basic
    @Column(name = "tenant_boss")
    private String tenantBoss;

    /**
     * 租户负责人电话
     */
    
    @Column(name = "tenant_tel")
    private String tenantTel;

    /**
     * 资源类型
     */
    @Basic
    @Column(name = "resource_type")
    private String resourceType;

    /**
     * 文件数
     */
    @Basic
    @Column(name = "file_count")
    private Integer fileCount;

    /**
     * ip地址，
     */
    @Transient
    private String ipAddr;
    
    /**
     * 服务名
     */
    @Transient
    private String serviceName;
    
    /**
     * 路径
     */
    @Transient
    private String path;
    
    /**
     * 队列名
     */
    @Transient
    private String sequenceName;
    /**
     * 存储
     */
    @Basic
    @Column(name = "storage")
    private String storage;

    /**
     * 存储使用量
     */
    @Basic
    @Column(name = "storage_usage")
    private String storageUsage;

    /**
     * 存储使用量占比
     */
    @Basic
    @Column(name = "storage_usage_rate")
    private Double storageUsageRate;

    /**
     * cpu 核数
     */
    @Basic
    @Column(name = "cpu_num")
    private String cpuNum;

    /**
     * cpu 最大数
     */
    @Basic
    @Column(name = "cpu_max")
    private String cpuMax;

    /**
     * cpu 平均数
     */
    @Basic
    @Column(name = "cpu_avg")
    private String cpuAvg;

    /**
     * 内存大小
     */
    @Basic
    @Column(name = "memory_size")
    private String memorySize;

    /**
     * 内存最大值
     */
    @Basic
    @Column(name = "memory_max")
    private String memoryMax;

    /**
     * 内存平均值
     */
    @Basic
    @Column(name = "memory_avg")
    private String memoryAvg;

    /**
     * 申请日期
     */
    @Basic
    @Column(name = "ask_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date askDate;

    /**
     * 变更日期
     */
    @Basic
    @Column(name = "change_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date changeDate;

    /**
     * 开放日期
     */
    @Basic
    @Column(name = "open_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date openDate;

    /**
     * 记录Id,用于标识一条记录，不同于表ID，在表中没有相应字段,作为整合其他表时连接字段
     */
    @Transient
    private String recordId;
    
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

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

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
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

    public String getCpuMax() {
        return cpuMax;
    }

    public void setCpuMax(String cpuMax) {
        this.cpuMax = cpuMax;
    }

    public String getCpuAvg() {
        return cpuAvg;
    }

    public void setCpuAvg(String cpuAvg) {
        this.cpuAvg = cpuAvg;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getMemoryMax() {
        return memoryMax;
    }

    public void setMemoryMax(String memoryMax) {
        this.memoryMax = memoryMax;
    }

    public String getMemoryAvg() {
        return memoryAvg;
    }

    public void setMemoryAvg(String memoryAvg) {
        this.memoryAvg = memoryAvg;
    }

    public Date getAskDate() {
        return askDate;
    }

    public void setAskDate(Date askDate) {
        this.askDate = askDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public DisTenantEntity(String serviceType, String tenantName, String resourceType,
                           String ipAddr, String serviceName, String path, String sequenceName,
                           String storage, String cpuNum, String memorySize, Date askDate,
                           Date changeDate, Date openDate, String recordId) {
        super();
        this.serviceType = serviceType;
        this.tenantName = tenantName;
        this.resourceType = resourceType;
        this.ipAddr = ipAddr;
        this.serviceName = serviceName;
        this.path = path;
        this.sequenceName = sequenceName;
        this.storage = storage;
        this.cpuNum = cpuNum;
        this.memorySize = memorySize;
        this.askDate = askDate;
        this.changeDate = changeDate;
        this.openDate = openDate;
        this.recordId = recordId;
    }
    
    public DisTenantEntity(String serviceType, String tenantName, Integer tenantLevel,
                           String tenantBoss, String tenantTel, String resourceType,
                           String ipAddr, String serviceName, String path, String sequenceName,
                           String storage, String cpuNum, String memorySize, Date askDate,
                           Date changeDate, Date openDate, String recordId) {
        super();
        this.serviceType = serviceType;
        this.tenantName = tenantName;
        this.tenantLevel = tenantLevel;
        this.tenantBoss = tenantBoss;
        this.tenantTel = tenantTel;
        this.resourceType = resourceType;
        this.ipAddr = ipAddr;
        this.serviceName = serviceName;
        this.path = path;
        this.sequenceName = sequenceName;
        this.storage = storage;
        this.cpuNum = cpuNum;
        this.memorySize = memorySize;
        this.askDate = askDate;
        this.changeDate = changeDate;
        this.openDate = openDate;
        this.recordId = recordId;
    }

    public DisTenantEntity(String serviceType, String tenantName,
                           String typeName,String ipAddr, String serviceName,
                           String path, String sequenceName, String storage,
                           String cpuNum, String cpuMax, String cpuAvg,
                           String memory, String memoryMax, String memoryAvg, 
                           Date askDate,Date changeDate, Date openDate, String recordId) {
        super();
        this.serviceType = serviceType;
        this.tenantName = tenantName;
        this.resourceType = typeName;
        this.ipAddr = ipAddr;
        this.serviceName = serviceName;
        this.path = path;
        this.sequenceName = sequenceName;
        this.storage = storage;
        this.cpuNum = cpuNum;
        this.cpuMax = cpuMax;
        this.cpuAvg = cpuAvg;
        this.memorySize = memory;
        this.memoryMax = memoryMax;
        this.memoryAvg = memoryAvg;
        this.askDate = askDate;
        this.changeDate = changeDate;
        this.openDate = openDate;
        this.recordId = recordId;
    }

    public DisTenantEntity(String serviceType, String tenantName,
                           String typeName,String ipAddr, String serviceName,
                           String path, String sequenceName, String storage,
                           String cpuNum, String memory, Double storageUsage,
                           Date askDate,Date changeDate, Date openDate, String recordId) {
        super();
        this.serviceType = serviceType;
        this.tenantName = tenantName;
        this.resourceType = typeName;
        this.ipAddr = ipAddr;
        this.serviceName = serviceName;
        this.path = path;
        this.sequenceName = sequenceName;
        this.storage = storage;
        this.cpuNum = cpuNum;
        this.memorySize = memory;
        this.storageUsage = storageUsage+"";
        this.askDate = askDate;
        this.changeDate = changeDate;
        this.openDate = openDate;
        this.recordId = recordId;
    }
    
    public DisTenantEntity(String serviceType, String tenantName,
                           String typeName,String ipAddr, String serviceName,
                           String path, String sequenceName, String storage,
                           String cpuNum, String memory, Integer fileNum,
                           Date askDate,Date changeDate, Date openDate, String recordId) {
        super();
        this.serviceType = serviceType;
        this.tenantName = tenantName;
        this.resourceType = typeName;
        this.ipAddr = ipAddr;
        this.serviceName = serviceName;
        this.path = path;
        this.sequenceName = sequenceName;
        this.storage = storage;
        this.cpuNum = cpuNum;
        this.memorySize = memory;
        this.fileCount = fileNum;
        this.askDate = askDate;
        this.changeDate = changeDate;
        this.openDate = openDate;
        this.recordId = recordId;
    }
    
    public DisTenantEntity() {
        super();
    }

    public DisTenantEntity(long tdId, String serviceType, String tenantName, Integer tenantLevel,
                           String tenantBoss, String tenantTel, String resourceType,
                           Integer fileCount, String storage, String storageUsage,
                           Double storageUsageRate, String cpuNum, String cpuMax, String cpuAvg,
                           String memorySize, String memoryMax, String memoryAvg, Date askDate,
                           Date changeDate, Date openDate) {
        super();
        this.tdId = tdId;
        this.serviceType = serviceType;
        this.tenantName = tenantName;
        this.tenantLevel = tenantLevel;
        this.tenantBoss = tenantBoss;
        this.tenantTel = tenantTel;
        this.resourceType = resourceType;
        this.fileCount = fileCount;
        this.storage = storage;
        this.storageUsage = storageUsage;
        this.storageUsageRate = storageUsageRate;
        this.cpuNum = cpuNum;
        this.cpuMax = cpuMax;
        this.cpuAvg = cpuAvg;
        this.memorySize = memorySize;
        this.memoryMax = memoryMax;
        this.memoryAvg = memoryAvg;
        this.askDate = askDate;
        this.changeDate = changeDate;
        this.openDate = openDate;
    }
    
    @Override
    public String toString() {
        return "DisTenantEntity [tdId=" + tdId + ", serviceType=" + serviceType + ", tenantName="
               + tenantName + ", tenantLevel=" + tenantLevel + ", tenantBoss=" + tenantBoss
               + ", tenantTel=" + tenantTel + ", resourceType=" + resourceType + ", fileCount="
               + fileCount + ", storage=" + storage + ", storageUsage=" + storageUsage
               + ", storageUsageRate=" + storageUsageRate + ", cpuNum=" + cpuNum + ", cpuMax="
               + cpuMax + ", cpuAvg=" + cpuAvg + ", memorySize=" + memorySize + ", memoryMax="
               + memoryMax + ", memoryAvg=" + memoryAvg + ", askDate=" + askDate + ", changeDate="
               + changeDate + ", openDate=" + openDate + "]";
    }

    

}
