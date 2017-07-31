/*
 * 文件名：ResourceAccountMidEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年7月31日
 */

package com.bonc.nerv.tioa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * 
 * @author yuanpeng
 * @version 2017年7月31日
 * @see ResourceAccountMidEntity
 * @since
 */
@Entity
public class ResourceAccountMidEntity {

    /**
     * 表id 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ramId;
    
    /**
     * 资源类型
     */
    private String type;
    
    /**
     * 租户名
     */
    private String tenantName;
    
    /**
     * 租户账号
     */
    private String tenantAccount;
    
    /**
     * 队列名
     */
    private String seqName;

    public Long getRamId() {
        return ramId;
    }

    public void setRamId(Long ramId) {
        this.ramId = ramId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantAccount() {
        return tenantAccount;
    }

    public void setTenantAccount(String tenantAccount) {
        this.tenantAccount = tenantAccount;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    @Override
    public String toString() {
        return "ResourceAccountMidEntity [ramId=" + ramId + ", type=" + type + ", tenantName="
               + tenantName + ", tenantAccount=" + tenantAccount + ", seqName=" + seqName + "]";
    }

    
}
