package com.example.springboot.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * <h4>springboot</h4>
 * <p></p>
 *
 * @author : shenbh
 * @date : 2022-05-18 11:54
 **/
@Component
public class InterceptorConfig implements WebMvcConfigurer {
    //所要拦截的请求路径
    String[] addPathPatterns = {
            "/index"
    };

    //不需要拦截的请求路径
    String[] excludePathPatterns = {
            "/user/user_exit","/user/login"
    };

    //mvc:interceptor class=""
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
