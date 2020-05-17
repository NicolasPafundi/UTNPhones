package com.utn.TPFinal.Config;

import com.utn.TPFinal.Session.SessionFilter;
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