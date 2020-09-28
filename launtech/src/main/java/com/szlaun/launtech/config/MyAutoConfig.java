package com.szlaun.launtech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/9/22 13:49
 * @Version V1.0
 **/
@Configuration
public class MyAutoConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


}
