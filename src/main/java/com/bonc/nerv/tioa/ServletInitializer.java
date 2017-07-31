package com.bonc.nerv.tioa;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * ServletInitializer
 * 〈功能详细描述〉
 * @author ke_wang
 * @version 2016年6月13日
 * @see ServletInitializer
 * @since ServletInitializer1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {  
	  
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(WebAppConfig.class);  
    }  
}  
