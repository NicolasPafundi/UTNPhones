package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
@RequestMapping("/Call")
public class CallApiController {

    private final CallService callService;

    @Autowired
    public CallApiController(CallService callService){
        this.callService = callService;
    }

    @GetMapping("/1")
    public Call getCallFromApi(){
        return callService.getById(47);
    }


}
