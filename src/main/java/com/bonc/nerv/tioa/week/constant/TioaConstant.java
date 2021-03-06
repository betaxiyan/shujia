package com.bonc.nerv.tioa.week.constant;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yuanpeng
 * @version 2017年7月31日
 * @see TioaConstant
 * @since
 */
public class TioaConstant {
    
    /**
     * 服务类型：内部
     */
    public static final Integer TENANT_INTERNAL = 10;
    
    /**
     * 服务类型：外部
     */
    public static final Integer TENANT_EXTERNAL = 20;
    
    
    /**
     * 租户分类：近期租户
     */
    public static final Integer TENANT_RECENT = 10;
    
    
    /**
     * 租户分类：历史租户
     */
    public static final Integer TENANT_HISTORICAL = 20;
    
    /**
     * 是否签署合同：是
     */
    public static final Integer TENANT_CONTRACT = 10;
    
    /**
     * 是否签署合同：否
     */
    public static final Integer TENANT_NO_CONTRACT = 20;
    
    /**
     * 租户级别：大，30
     */
    public static final Integer TENANT_LEVEL_LARGE = 30;
    
    /**
     * 租户级别：中，20
     */
    public static final Integer TENANT_LEVEL_MIDDLE = 20;
    
    /**
     * 租户级别：小，10
     */
    public static final Integer TENANT_LEVEL_SMALL = 10;
  
    /**
     * 资源状态: 删除
     */
    public static final Integer RESOURCE_STATE_DELETE = 1;
    
    /**
     * 资源状态:未删除
     */
    public static final Integer RESOURCE_STATE_NORMAL = 0;
}
