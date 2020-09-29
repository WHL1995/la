package com.szlaun.launtech.config;

import com.szlaun.launtech.handler.ActionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 拦截器
 * @Author lizhiming
 * @Date 2020/9/22 13:49
 * @Version V1.0
 **/
@Configuration
public class MyAutoConfig implements WebMvcConfigurer {

    @Autowired
    private ActionInterceptor actionInterceptor;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 给拦截器链添加资源拦截规则
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
    }

    /**
     * 给拦截器链添加拦截规则
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(actionInterceptor).addPathPatterns("/**");
    }
}
