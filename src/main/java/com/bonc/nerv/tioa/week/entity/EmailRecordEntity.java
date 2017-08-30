/*
 * 文件名：EmailRecordEnitity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.week.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * 邮件内容记录表
 * @author yuanpeng
 * @version 2017年8月6日
 * @see EmailRecordEnitity
 * @since
 */
@Entity
public class EmailRecordEntity {

    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long erId;
    
    /**
     * 收件人
     */
    @ManyToOne
    private RecipientsEntity recipientsEntity;
    
    /**
     * 邮件内容
     */
    private String comment;
    
    /**
     * 发送时间
     */
    private Date sendTime;
    
    /**
     * 是否成功
     */
    private Integer result;
    
    /**
     * 失败原因
     */
    private String remark;

   
    
}
