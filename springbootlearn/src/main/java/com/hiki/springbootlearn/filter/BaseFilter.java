package com.hiki.springbootlearn.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*",filterName = "baseFilter")
public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest arequest = (HttpServletRequest) request;
        System.out.println("Filter success!url is :"+arequest.getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
