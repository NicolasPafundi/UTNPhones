package com.utn.TPFinal.services.integration;

import com.utn.TPFinal.model.entities.Call;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class IntegrationComponent {

    private RestTemplate rest;
    private static String url = "http://localhost:8080/calls/1";

    @PostConstruct
    private void init() {
        rest = new RestTemplateBuilder()
                .build();
    }

    public Call getCallsFromApi() {
        return rest.getForObject(url, Call.class);
    }

}
