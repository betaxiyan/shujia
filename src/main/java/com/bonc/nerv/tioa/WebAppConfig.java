package com.bonc.nerv.tioa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 入口
 * @author yuanpeng
 * @version 2017年3月30日
 * @see WebAppConfig
 * @since WebAppConfig1.0
 */
@EnableScheduling
@SpringBootApplication
public class WebAppConfig extends WebMvcConfigurerAdapter{
    
    /**
     * 
     * Description: <br>
     * main方法
     * @param args String[]
     * @see
     */
    public static void main(String[] args) {
        SpringApplication.run(WebAppConfig.class, args);
        System.out.println("Tool项目启动成功");
    }
}