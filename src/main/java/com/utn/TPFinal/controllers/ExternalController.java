package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.services.IntegrationService;
import com.utn.TPFinal.services.integration.IntegrationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
public class ExternalController {

    @Autowired
    IntegrationService integrationService;

    @GetMapping("/call")
    public Call getCall(){
        return integrationService.getCall();
    }

}
