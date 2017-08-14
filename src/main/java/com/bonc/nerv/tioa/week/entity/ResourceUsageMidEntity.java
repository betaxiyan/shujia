/*
 * 文件名：WebServerMidEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 资源存储使用量统计
 * @author yuanpeng
 * @version 2017年8月14日
 * @see WebServerMidEntity
 * @since
 */

@Entity
public class ResourceUsageMidEntity {

    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rumId;
    
    /**
     * 资源类型
     */
    private String type;
    
    /**
     * 关键字
     */
    private String keyword;
    
    /**
     * 存储使用量（GB为单位）
     */
    private Double storageUsage;

    public Long getRumId() {
        return rumId;
    }

    public void setRumId(Long rumId) {
        this.rumId = rumId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Double getStorageUsage() {
        return storageUsage;
    }

    public void setStorageUsage(Double storageUsage) {
        this.storageUsage = storageUsage;
    }
    
    
}
