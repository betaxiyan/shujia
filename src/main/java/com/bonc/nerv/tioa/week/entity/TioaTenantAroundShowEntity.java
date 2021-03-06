/*
 * 文件名：TioaTenantArountShowEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.week.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 租户周边信息展示表
 * @author yuanpeng
 * @version 2017年8月6日
 * @see TioaTenantArountShowEntity
 * @since
 */

@Entity
@Table(name = "tioa_tenant_around_show")
public class TioaTenantAroundShowEntity {

    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ttaId;
    
    /**     * 租户id
     */
    private String tenantId;
    
    /**
     * 租户名
     */
    private String tenantName;
    
    /**
     * 租户级别
     */
    private Integer tenantLevel;
    
    /**
     * 租户负责人
     */
    private String tenantBoss;
    
    /**
     * 租户负责人电话
     */
    private String tenantTel;
    
    /**
     * 统一平台个数
     */
    private Integer numOfUnifiedPlatform;
    
    
    /**
     * 4A个数
     */
    private Integer numOf4a;
    
    /**
     * 需求
     */
    private String tenantReqirement;
    
    /**
     * 平台接口人
     */
    private String tenantInterface;
    

    public Integer getNumOfUnifiedPlatform() {
        return numOfUnifiedPlatform;
    }

    public void setNumOfUnifiedPlatform(Integer numOfUnifiedPlatform) {
        this.numOfUnifiedPlatform = numOfUnifiedPlatform;
    }

    public Integer getNumOf4a() {
        return numOf4a;
    }

    public void setNumOf4a(Integer numOf4a) {
        this.numOf4a = numOf4a;
    }

    public String getTenantReqirement() {
        return tenantReqirement;
    }

    public void setTenantReqirement(String tenantReqirement) {
        this.tenantReqirement = tenantReqirement;
    }

    public Long getTtaId() {
        return ttaId;
    }

    public void setTtaId(Long ttaId) {
        this.ttaId = ttaId;
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

    public String getTenantInterface() {
        return tenantInterface;
    }

    public void setTenantInterface(String tenantInterface) {
        this.tenantInterface = tenantInterface;
    }

    @Override
    public String toString() {
        return "TioaTenantAroundShowEntity [ttaId=" + ttaId + ", tenantId=" + tenantId
               + ", tenantName=" + tenantName + ", tenantLevel=" + tenantLevel + ", tenantBoss="
               + tenantBoss + ", tenantTel=" + tenantTel + ", numOfUnifiedPlatform="
               + numOfUnifiedPlatform + ", numOf4a=" + numOf4a + ", tenantReqirement="
               + tenantReqirement + ", tenantInterface=" + tenantInterface + "]";
    }

   
    
    
}
