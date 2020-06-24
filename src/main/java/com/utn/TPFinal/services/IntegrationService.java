package com.utn.TPFinal.services;

import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.services.integration.IntegrationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegrationService {

    @Autowired
    IntegrationComponent integrationComponent;

    public Call getCall(){
        return integrationComponent.getCallFromApi();
    }
}
