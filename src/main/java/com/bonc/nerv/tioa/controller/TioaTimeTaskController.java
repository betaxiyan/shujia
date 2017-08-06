/*
 * 文件名：TioaTimeTaskController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：yuanpeng
 * 修改时间：2017年8月6日
 */

package com.bonc.nerv.tioa.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * 定时任务控制器
 * @author yuanpeng
 * @version 2017年8月6日
 * @see TioaTimeTaskController
 * @since
 */

@Component
public class TioaTimeTaskController {

    /**
     * 
     * Description: <br>
     * 定时任务，获取底层数据到展示表中
     * 0 0 0 ? * MON 表示每个星期三0点 
     * 0 0 12 * * ?  每天中午12点触发
     * 0 0/15 * * * ?  测试用每15分钟触发
     * 0 0 2 * * ? 每天凌晨2点触发
     * @see
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void getDataToShowTask(){
        /**
         * 每日凌晨2时，调用getMidDataToTtd()、getMidDateToTtl()方法
         */
    }
    
    /**
     * 
     * Description: <br>
     * 定时任务，    发送租户到期邮件给接口人
     * 0 0 0 ? * MON 表示每个星期三0点 
     * 0 0 12 * * ?  每天中午12点触发
     * 0 0/15 * * * ?  测试用每15分钟触发
     * 0 0 2 * * ? 每天凌晨2点触发
     * @see
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void sendEmailToInterface(){
        
    }
}
