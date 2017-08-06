/*
 * 文件名：CpuMemoryMid.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 使用CPu内存资源统计信息表
 * @author yuanpeng
 * @version 2017年8月6日
 * @see CpuMemoryMidEntity
 * @since
 */
@Entity
@Table(name = "cpu_memory_mid")
public class CpuMemoryMidEntity {

    
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cmmId;
    
    /**
     * 队列名
     */
    private String sequenceName;
    
    /**
     * cpu周平均值
     */
    private String cpuAvg;
    
    /**
     * cpu使用最大值
     */
    private String cpuMax;
    
    /**
     * 内存使用平均值
     */
    private String memoryAvg;
    
    /**
     *内存使用最大值
     */
    private String memoryMax;
    
    public Long getCmmId() {
        return cmmId;
    }
    public void setCmmId(Long cmmId) {
        this.cmmId = cmmId;
    }
    public String getSequenceName() {
        return sequenceName;
    }
    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }
    public String getCpuAvg() {
        return cpuAvg;
    }
    public void setCpuAvg(String cpuAvg) {
        this.cpuAvg = cpuAvg;
    }
    public String getCpuMax() {
        return cpuMax;
    }
    public void setCpuMax(String cpuMax) {
        this.cpuMax = cpuMax;
    }
    public String getMemoryAvg() {
        return memoryAvg;
    }
    public void setMemoryAvg(String memoryAvg) {
        this.memoryAvg = memoryAvg;
    }
    public String getMemoryMax() {
        return memoryMax;
    }
    public void setMemoryMax(String memoryMax) {
        this.memoryMax = memoryMax;
    }
    @Override
    public String toString() {
        return "CpuMemoryMidEntity [cmmId=" + cmmId + ", sequenceName=" + sequenceName
               + ", cpuAvg=" + cpuAvg + ", cpuMax=" + cpuMax + ", memoryAvg=" + memoryAvg
               + ", memoryMax=" + memoryMax + "]";
    }
    
}
