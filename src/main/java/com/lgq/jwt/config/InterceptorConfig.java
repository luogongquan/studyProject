package com.lgq.jwt.config;

import com.lgq.jwt.filter.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: luogongquan
 * @date: 2023/4/11 9:56
 * @Description:
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/test")  //拦截url
                .excludePathPatterns("/user/login");    //放行url
    }
}
