package com.hibernate2levelcache.filterConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class DistFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(DistFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("[DistFilter] - inside doFilter method");
        logger.info("[Local port]"+ servletRequest.getLocalPort());

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.info("[Method name]"+httpServletRequest.getMethod());
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
