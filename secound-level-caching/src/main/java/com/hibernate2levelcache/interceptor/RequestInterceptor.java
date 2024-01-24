package com.hibernate2levelcache.interceptor;

import com.hibernate2levelcache.exception.BadDataException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("auth-token") == null){
            throw new BadDataException("Authentication failed");
        }
        return true;
    }
}
