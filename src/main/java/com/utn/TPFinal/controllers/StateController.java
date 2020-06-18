package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.State;
import com.utn.TPFinal.services.StateService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/api/State")
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }


    @GetMapping("/Employee")
    public ResponseEntity<List<State>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<State> states = stateService.getAll();
            return (states.size() > 0) ? ResponseEntity.ok(states) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
