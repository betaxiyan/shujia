/*
 * 文件名：FileCountEntity.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.day.entity;

/**
 * 
 * 文件统计日报
 * @author yuanpeng
 * @version 2017年8月6日
 * @see FileCountEntity
 * @since
 */
public class FileCountEntity {

    /**
     * id
     */
    private Long id;
    
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
    private Long fileNum;
    
    /**
     * 文件夹数
     */
    private Long folderNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFileNum() {
        return fileNum;
    }

    public void setFileNum(Long fileNum) {
        this.fileNum = fileNum;
    }

    public Long getFolderNum() {
        return folderNum;
    }

    public void setFolderNum(Long folderNum) {
        this.folderNum = folderNum;
    }
    
    
}
