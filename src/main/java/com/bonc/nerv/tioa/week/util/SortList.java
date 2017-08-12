/*
 * 文件名：SortList.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月11日
 */

package com.bonc.nerv.tioa.week.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * 排序工具类
 * @author yuanpeng
 * @version 2017年8月11日
 * @see SortList  
 * @param <E> 
 * @since
 */
public class SortList<E>{
    
    /**
     * 
     * Description: <br>
     * 排序方法
     * @param list   
     * @param method  
     * @param sort   
     * @see
     */
    public void Sort(List<E> list, final String method, final String sort){
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try{
                    java.lang.reflect.Method m1 = ((E)a).getClass().getMethod(method, null);
                    java.lang.reflect.Method m2 = ((E)b).getClass().getMethod(method, null);
                    if(sort != null && "desc".equals(sort)){//倒序
                        ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a), null).toString());
                    }else{//正序
                        ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());
                    }
                    }catch(NoSuchMethodException ne){
                        System.out.println(ne);
                    }catch(IllegalAccessException ie){
                        System.out.println(ie);
                    }catch(InvocationTargetException it){
                        System.out.println(it);
                    }
                return ret;
            }
        });
    }
}