package com.example.mmall.config;

import com.example.mmall.filter.UserFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import java.io.FilterReader;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserFilter());
            filterRegistrationBean.addUrlPatterns("/cart/add/*","/orders/*");
            return filterRegistrationBean;
    }
}
