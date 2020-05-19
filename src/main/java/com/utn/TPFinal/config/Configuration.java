package com.utn.TPFinal.config;

import com.utn.TPFinal.session.SessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@org.springframework.context.annotation.Configuration
@EnableScheduling
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;


    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/X/*");
        return registration;
    }
}