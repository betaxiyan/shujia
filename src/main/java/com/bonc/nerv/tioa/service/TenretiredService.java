/*
 * 文件名：TenretiredService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：ymm
 * 修改时间：2017年7月28日
 */

package com.bonc.nerv.tioa.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bonc.nerv.tioa.dao.TenretiredDao;
import com.bonc.nerv.tioa.entity.SearchTenretiredData;
import com.bonc.nerv.tioa.entity.TenretiredEntity;
import com.bonc.nerv.tioa.util.ResultPager;

/**
 * 
 * 已退租户情况service层
 * @author ymm
 * @version 2017年8月2日
 * @see TenretiredService
 * @since
 */
@Service
public interface TenretiredService {
   
    /**
     * Description:添加分页的查询
     * @param searchData 查询条件对象
     * @param start
     * @param length
     * @param draw
     * @return  查询结果
     * @see
     */
    String findTenretiredList(SearchTenretiredData searchData, Integer start, Integer length,
                            String draw);
    
    /**
     * Description: 多条件查询方法
     * @param bean  查询条件对象
     * @return 返回查询条件
     * @see
     */
    Specification<TenretiredEntity> tenretiredSearch(SearchTenretiredData bean);
    
    /**
     * 根据条件导出用户记录信息
     * @param searchData 查询实体对象
     * @return 集合
     * @see
     */
    List<TenretiredEntity> exportTenretired(SearchTenretiredData searchData);
    
    /**
     * 
     * Description: 保存新增退租用户
     * @param tenretiredEntity  已退租户实体类
     * @return 返回添加结果
     * @see
     */
    String save(TenretiredEntity tenretiredEntity);
    
    /**
     * 
     * Description: 保存新增退租用户
     * @param tenretiredEntity 已退租户实体类
     * @return 返回更新结果
     * @see
     */
    String update(TenretiredEntity tenretiredEntity);
    
    /**
     * 
     * 根据id删除数据
     * @param tlId 表id
     * @return 返回验证结果
     * @see
     */
    boolean validateByTlId(long tlId);
    
    /**
     * Description: 根据tlId得到一条记录
     * @param tlId  表id
     * @return  实体信息
     * @see
     */
    TenretiredEntity findOne(long tlId);
    
    /**
     * 删除一条已退租户记录
     * @param tlId 
     * @see
     */
    void delete(long tlId);
    
    /**
     * 导出Excel方法
     * @param list 返回集合
     * @param response  
     * @see
     */
    void getExcel(List<TenretiredEntity> list, HttpServletResponse response);
    
    /**
     * Description: 数据放到list集合中
     * @param list  数组对象
     * @return  列表集合
     * @see
     */
    List<String[]> getTenList(List<TenretiredEntity> list);
    
}
    
    

