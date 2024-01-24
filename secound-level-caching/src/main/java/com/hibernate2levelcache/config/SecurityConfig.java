package com.hibernate2levelcache.config;

import com.hibernate2levelcache.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final RequestInterceptor requestInterceptor;

    public SecurityConfig(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(requestInterceptor);
    }
}
