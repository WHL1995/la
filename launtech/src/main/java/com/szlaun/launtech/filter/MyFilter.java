package com.szlaun.launtech.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description 过滤器啥也没做
 * @Author lizhiming
 * @Date 2020/9/24 13:39
 * @Version V1.0
 **/
@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("doFilter....");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
//        System.out.println("destroy....");
    }
}
