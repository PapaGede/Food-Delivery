package com.carrotinstitutefooddelivery.config;

import com.carrotinstitutefooddelivery.filter.AdminFilter;
import com.carrotinstitutefooddelivery.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FilterConfig {

    @Bean
    FilterRegistrationBean<AuthFilter>authFilterFilterRegistration(AuthFilter authFilter){
        var filterRegistrationBean = new FilterRegistrationBean<AuthFilter>();
        filterRegistrationBean.setFilter(authFilter);
        filterRegistrationBean.setUrlPatterns(List.of("/api/*"));
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }
    @Bean
    FilterRegistrationBean<AdminFilter>adminFilterFilterRegistration(AdminFilter adminFilter){
        var filterRegistrationBean = new FilterRegistrationBean<AdminFilter>();
        filterRegistrationBean.setFilter(adminFilter);
        filterRegistrationBean.setUrlPatterns(List.of("/api/admin/*"));
        filterRegistrationBean.setOrder(2);

        return filterRegistrationBean;
    }
}
