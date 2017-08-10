/*
 * 文件名：RestfulTableMgrServiceImpl.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：HCN 修改时间：2017年8月10日
 */

package com.bonc.nerv.tioa.week.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.bonc.nerv.tioa.week.service.RestfulTableMgrService;
import com.bonc.nerv.tioa.week.util.WebClientUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 接口健康信息服务层实现类 封装接口健康信息给前台
 * 
 * @author HCN
 * @version 2017年8月10日
 * @see RestfulTableMgrServiceImpl
 * @since
 */
@Service("restfulTableMgrService")
public class RestfulTableMgrServiceImpl implements RestfulTableMgrService {

    @SuppressWarnings("unchecked")
    @Override
    public String restfulHealth() {

        // 封装调用结果给前台
        List<Map<String, Object>> reslist = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        // 接口返回的json字符串
        String result = "";
        // 格式化检查日期的SimpleDateFormat
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date checkDate = new Date();
        // 两个接口的状态
        String status = null;
        String jsonString = "";
        boolean success = true;
        // 请求health接口，如果请求失败，就会在mapper.readValue处出异常，将success置为false即可
        jsonString = WebClientUtil.doGet("http://192.168.50.40:8080/bonc-nerv/health", null);
        // 调用bonc-nerv的health接口
        Map<String, String> m = null;
        try {
            m = mapper.readValue(jsonString, Map.class);
        } catch (Exception e1) {
            e1.printStackTrace();
            success = false;
        }
        if (success) {
            // 如果health接口能返回up，说明程序没挂，故两个接口也没问题
            if (m.get("status").equals("UP")) {
                status = "UP";
            }
        } else {
            status = "DOWN";
        }

        // 封装信息给前台显示

        // findAllAccount接口
        String url01 = "http://192.168.50.40:8080/bonc-nerv/findAllAccount";
        Map<String, Object> map1 = new HashMap<>();
        map1.put("restfulName", "findAllAccount");
        map1.put("restfulURL", url01);
        map1.put("status", status);
        map1.put("checkDate", myFmt.format(checkDate));
        reslist.add(map1);

        // findFileCount接口
        String url02 = "http://192.168.50.40:8080/bonc-nerv/findFileCount";
        Map<String, Object> map2 = new HashMap<>();
        map2.put("restfulName", "findFileCount");
        map2.put("restfulURL", url02);
        map2.put("status", status);
        map2.put("checkDate", myFmt.format(checkDate));
        reslist.add(map2);

        // 封装到data中
        map.put("data", reslist);
        try {
            result = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

        return result;
    }

}
