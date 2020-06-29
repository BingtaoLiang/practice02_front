package com.practice.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List exclude = new ArrayList(); // 不拦截列表
        exclude.add("/");
        exclude.add("/denglu");
        exclude.add("/addlogin");
        exclude.add("/logout");
        exclude.add("/register");
        exclude.add("/addregister");
        exclude.add("/sendcode");
        exclude.add("/phonelogin");
        exclude.add("/css/**");
        exclude.add("/fonts/**");
        exclude.add("/js/**");
        exclude.add("/docs/**");

        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude);
//
//        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }
}