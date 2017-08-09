/*
 * 文件名：FileCountEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.day.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * 文件统计日报
 * @author yuanpeng
 * @version 2017年8月6日
 * @see FileCountEntity
 * @since
 */
@Entity
public class FileCountEntity {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long fcId;
    
    /**
     * 系统日期
     */
    private String sysDate;
    
    /**
     * 租户名
     */
    private String tenant;
    
    /**
     * 租户账号
     */
    private String account;
    
    /**
     * 资源类型
     */
    private String type;
    
    /**
     * 文件数
     */
    private Integer fileNum;
    
    /**
     * 文件夹数
     */
    private Integer folderNum;
    
    /**
     * 总数
     */
    private Integer totalNum;
    

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Long getFcId() {
        return fcId;
    }

    public void setFcId(Long fcId) {
        this.fcId = fcId;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public Integer getFolderNum() {
        return folderNum;
    }

    public void setFolderNum(Integer folderNum) {
        this.folderNum = folderNum;
    }


    
}
