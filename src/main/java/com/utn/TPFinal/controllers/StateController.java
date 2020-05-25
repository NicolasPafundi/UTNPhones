package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.State;
import com.utn.TPFinal.model.entities.User;
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


    @GetMapping("/")
    public ResponseEntity<List<State>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<State> states = stateService.getAll();
            return (states.size() > 0) ? ResponseEntity.ok(states) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                State state = stateService.getById(id);
                return (state != null) ? ResponseEntity.ok(state) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody State state){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                return ResponseEntity.status(HttpStatus.CREATED).body(stateService.add(state));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody State state) throws Exception {
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                stateService.update(state);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                stateService.remove(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
