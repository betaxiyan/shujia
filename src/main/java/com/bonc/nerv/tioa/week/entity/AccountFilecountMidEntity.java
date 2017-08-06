/*
 * 文件名：accountFilecountMidEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年7月31日
 */

package com.bonc.nerv.tioa.week.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 账号和文件数数据实体类
 * @author yuanpeng
 * @version 2017年7月31日
 * @see accountFilecountMidEntity
 * @since
 */
@Entity
@Table(name="account_filecount_mid")
public class AccountFilecountMidEntity {

    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long afmId;
    
    /**
     * 系统时间
     */
    private String sysDate;
    
    /**
     * 资源类型
     */
    private String type;
    
    /**
     * 队列名
     */
    private String seqName;
    
    /**
     * 文件数
     */
    private Integer fileNum;
    
    /**
     * 文件夹数
     */
    private Integer dfileNum;

    public Long getAfmId() {
        return afmId;
    }

    public void setAfmId(Long afmId) {
        this.afmId = afmId;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public Integer getDfileNum() {
        return dfileNum;
    }

    public void setDfileNum(Integer dfileNum) {
        this.dfileNum = dfileNum;
    }

    @Override
    public String toString() {
        return "accountFilecountMidEntity [afmId=" + afmId + ", sysDate=" + sysDate + ", type="
               + type + ", seqName=" + seqName + ", fileNum=" + fileNum + ", dfileNum=" + dfileNum
               + "]";
    }
    
    
}
