package com.example.cinema.config;

import com.example.cinema.interceptor.AdminInterceptor;
import com.example.cinema.interceptor.AudienceInteceptor;
import com.example.cinema.interceptor.ManagerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    public final static String SESSION_KEY = "user";
    @Autowired
    AudienceInteceptor audienceInteceptor;
    @Autowired
    AdminInterceptor adminInterceptor;
    @Autowired
    ManagerInterceptor managerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(audienceInteceptor).addPathPatterns("/user/**");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(managerInterceptor).addPathPatterns("/manager/**");
    }
}
