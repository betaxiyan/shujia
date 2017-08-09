/*
 * 文件名：FileCountServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.day.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.day.dao.FileCountDao;
import com.bonc.nerv.tioa.day.entity.FileCountEntity;
import com.bonc.nerv.tioa.day.service.FileCountService;
import com.bonc.nerv.tioa.week.entity.AccountFilecountMidEntity;
import com.bonc.nerv.tioa.week.entity.ResourceAccountMidEntity;
import com.bonc.nerv.tioa.week.service.ExternalRestService;

/**
 * 
 * 文件统计服务类的service实现类
 * @author yuanpeng
 * @version 2017年8月6日
 * @see FileCountServiceImpl
 * @since
 */
@Service
public class FileCountServiceImpl implements FileCountService {

    /**
     * fileCount的数据库交互类
     */
    @Autowired
    private FileCountDao fileCountDao;
    
    @Autowired
    private ExternalRestService externalRestService;
    
    @Override
    public FileCountEntity findOneFc(Long fcId) {
        
        FileCountEntity fileCountEntity = fileCountDao.findOne(fcId);
        return fileCountEntity;
    }

    @Override
    public List<FileCountEntity> findAllFc(String sysDate) {
        if(sysDate.length()<12){
            System.out.println("不能小于12个");
            return null;
        }
        
        List<FileCountEntity> fileCountEntities = fileCountDao.findBySysDate(sysDate);
        if( fileCountEntities.size() == 0){
            List<AccountFilecountMidEntity> accountFilecountMidEntes =  externalRestService.accFcountRest(sysDate);
            List<ResourceAccountMidEntity> resourceAccountMidEntities = externalRestService.resAccRest();
            
            Map<String,FileCountEntity> mapTemp = new HashMap<String,FileCountEntity>();
            for(AccountFilecountMidEntity accountFilecountMidEntity : accountFilecountMidEntes){
                FileCountEntity fileCountEntity = new FileCountEntity();
                String keyTemp = accountFilecountMidEntity.getType() + accountFilecountMidEntity.getSeqName();
                Integer fileNumTemp = accountFilecountMidEntity.getFileNum();
                Integer folderNumTemp = accountFilecountMidEntity.getDfileNum();
                String typeTemp = accountFilecountMidEntity.getType();
                fileCountEntity.setFileNum(fileNumTemp);
                fileCountEntity.setFolderNum(folderNumTemp);
                fileCountEntity.setTotalNum(fileNumTemp + folderNumTemp);
                fileCountEntity.setType(typeTemp);
                fileCountEntity.setSysDate(sysDate);
                mapTemp.put(keyTemp, fileCountEntity);
            }
            
            List<FileCountEntity> fileCountEntitiesTemp = new ArrayList<FileCountEntity>();
            for(ResourceAccountMidEntity resourceAccountMidEntity : resourceAccountMidEntities){
                FileCountEntity fileCountEntity = new FileCountEntity();
                if(mapTemp.containsKey(resourceAccountMidEntity.getType() + resourceAccountMidEntity.getSeqName())){
                    fileCountEntity = mapTemp.get(resourceAccountMidEntity.getType() + resourceAccountMidEntity.getSeqName());
                    fileCountEntity.setTenant(resourceAccountMidEntity.getTenantName());
                    fileCountEntity.setAccount(resourceAccountMidEntity.getTenantAccount());
                    fileCountEntitiesTemp.add(fileCountEntity);
                } else{
//                    fileCountEntity.setTenant(resourceAccountMidEntity.getTenantName());
//                    fileCountEntity.setAccount(resourceAccountMidEntity.getTenantAccount());
//                    fileCountEntity.setFileNum(0);
//                    fileCountEntity.setFolderNum(0);
//                    fileCountEntity.setTotalNum(0);
//                    fileCountEntitiesTemp.add(fileCountEntity);
                }
            }
            
            Map<String,FileCountEntity> fcMapTemp = new HashMap<String,FileCountEntity>();
            for(FileCountEntity fileCountEntity : fileCountEntitiesTemp){
                if(!fcMapTemp.containsKey(fileCountEntity.getTenant())){
                    fcMapTemp.put(fileCountEntity.getTenant(), fileCountEntity);
                }else{
                    FileCountEntity fctEntity =  fcMapTemp.get(fileCountEntity.getTenant());
                    if(fileCountEntity.getType().equals(fctEntity.getType())){
                        continue;
                    }else{
                        fctEntity.setFileNum(fctEntity.getFileNum() + fileCountEntity.getFileNum()); 
                        fctEntity.setFolderNum(fctEntity.getFolderNum() + fileCountEntity.getFolderNum());
                        fctEntity.setTotalNum(fctEntity.getTotalNum() + fileCountEntity.getTotalNum());
                        fcMapTemp.put(fileCountEntity.getTenant(), fctEntity);
                    }
                }
            }
            
            fileCountEntities = fileCountDao.save(fcMapTemp.values());
        }
        return fileCountEntities;
    }
    
    @Override
    public FileCountEntity addOneFc(FileCountEntity fileCountEntity) {
        
        FileCountEntity newFileCountEntity = fileCountDao.save(fileCountEntity);
        return newFileCountEntity;
    }

    @Override
    public void deleteOneFc(Long fcId) {
        
        fileCountDao.delete(fcId);
    }

    @Override
    public FileCountEntity editOneFc(FileCountEntity fileCountEntity) {
        
        FileCountEntity newFileCountEntity = fileCountDao.save(fileCountEntity);
        return newFileCountEntity;
    }

    
    
}
