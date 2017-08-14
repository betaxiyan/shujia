/*
 * 文件名：SearchTen.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年8月1日
 */

package com.bonc.nerv.tioa.week.entity;

/**
 * 查询条件实体类
 * @author YMM
 * @version 2017年8月14日
 * @see SearchTenretiredData
 * @since
 */

public class SearchTenretiredData {
    
    /**
     * 服务类型
     */
    private String serviceType;
    
    /**
     * 租户名称
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
     * 联系电话
     */
    private String tenantTel;
    
    /**
     * 平台接口人
     */
    private String  tenantInterface;

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }

    public String getTenantName()
    {
        return tenantName;
    }

    public void setTenantName(String tenantName)
    {
        this.tenantName = tenantName;
    }

    public Integer getTenantLevel()
    {
        return tenantLevel;
    }

    public void setTenantLevel(Integer tenantLevel)
    {
        this.tenantLevel = tenantLevel;
    }

    public String getTenantBoss()
    {
        return tenantBoss;
    }

    public void setTenantBoss(String tenantBoss)
    {
        this.tenantBoss = tenantBoss;
    }

    public String getTenantTel()
    {
        return tenantTel;
    }

    public void setTenantTel(String tenantTel)
    {
        this.tenantTel = tenantTel;
    }

    public String getTenantInterface()
    {
        return tenantInterface;
    }

    public void setTenantInterface(String tenantInterface)
    {
        this.tenantInterface = tenantInterface;
    }

}
