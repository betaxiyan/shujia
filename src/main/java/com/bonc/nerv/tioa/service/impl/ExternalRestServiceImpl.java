/*
 * 文件名：ExternalRestServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年7月31日
 */

package com.bonc.nerv.tioa.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.service.ExternalRestService;
import com.bonc.nerv.tioa.util.WebClientUtil;

/**
 * 
 * 调用外部restful接口的服务类
 * @author yuanpeng
 * @version 2017年7月31日
 * @see ExternalRestServiceImpl
 * @since
 */
@Service
public class ExternalRestServiceImpl implements ExternalRestService {

    public void resAccToDb() {
        // TODO Auto-generated method stub
        
    }

    public void accFcountToDb(String sysDate) {
        // TODO Auto-generated method stub
        String value = "1111";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", value);
        WebClientUtil.doGet("http://www.***.com", params);
    }

}
