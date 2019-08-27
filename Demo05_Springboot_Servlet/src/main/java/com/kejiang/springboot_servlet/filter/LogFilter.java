package com.kejiang.springboot_servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ Author：ke
 * @ Date： 2019/7/28 14:21
 * @ Version 1.0
 */

public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        logger.info("Request---coming");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
