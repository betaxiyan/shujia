/*
 * 文件名：SearchDisTenant.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Administrator
 * 修改时间：2017年8月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bonc.nerv.tioa.week.entity;

public class SearchDisTenant {
    private String serviceType;
    private String tenantName;
    private Integer tenantLevel;
    private String tenantBoss;
    private String tenantTel;

    
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

    
}
