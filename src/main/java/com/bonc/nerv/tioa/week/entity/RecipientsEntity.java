/*
 * 文件名：RecipientsEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月29日
 */

package com.bonc.nerv.tioa.week.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 收件人类
 * @author yuanpeng
 * @version 2017年8月29日
 * @see RecipientsEntity
 * @since
 */
@Entity
public class RecipientsEntity {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long recId;
    
    /**
     * 收件人名
     */
    private String recName;
    
    /**
     * 收件人地址
     */
    private String recAddr;
    
    /**
     * 是否启用
     */
    private Integer state;
    
    /**
     * 收件人类型
     */
    private Integer type;
    
    /**
     * 对应模板
     */
    @OneToOne
    private EmailModelEntity modelEntity;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecAddr() {
        return recAddr;
    }

    public void setRecAddr(String recAddr) {
        this.recAddr = recAddr;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public EmailModelEntity getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(EmailModelEntity modelEntity) {
        this.modelEntity = modelEntity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    
}
