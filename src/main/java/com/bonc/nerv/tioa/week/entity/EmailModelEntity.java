/*
 * 文件名：EmailModalEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月29日
 */

package com.bonc.nerv.tioa.week.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * email模板类
 * @author yuanpeng
 * @version 2017年8月29日
 * @see EmailModelEntity
 * @since
 */
@Entity
public class EmailModelEntity {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long emId;
    
    /**
     * 唯一编码
     */
    private String modelCode;
    
    /**
     * 模板
     */
    @Column(columnDefinition="TEXT")
    private String emailModel;

    public Long getEmId() {
        return emId;
    }

    public void setEmId(Long emId) {
        this.emId = emId;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getEmailModel() {
        return emailModel;
    }

    public void setEmailModel(String emailModel) {
        this.emailModel = emailModel;
    }
    
    
}
