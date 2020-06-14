package com.utn.TPFinal.controllers;

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
    private final SessionManager sessionManager;

    @Autowired
    public StateController(StateService stateService, SessionManager sessionManager) {
        this.stateService = stateService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/Client")
    public ResponseEntity<List<State>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<State> states = stateService.getAll();
            return (states.size() > 0) ? ResponseEntity.ok(states) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<State> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            State state = stateService.getById(id);
            return (state != null) ? ResponseEntity.ok(state) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody State state){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(stateService.add(state));
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/Employee/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody State state) throws Exception, ValidationException {
        try{
            stateService.update(state);
            return ResponseEntity.ok().build();
        }catch (Exception | ValidationException ex){
            throw ex;
        }
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            stateService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
