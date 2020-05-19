package com.utn.TPFinal.controllers;

import com.utn.TPFinal.domain.Entities.*;
import com.utn.TPFinal.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/State")
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }


    @GetMapping("/")
    public List<State> getAll(){
        try{
            return stateService.getAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public State getById(@PathVariable Integer id){
        try{
            return stateService.getById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer add(@RequestBody State state){
        try{
            return stateService.add(state);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void update(@RequestBody State state) throws Exception {
        try{
            stateService.update(state);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        try{
            stateService.remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
