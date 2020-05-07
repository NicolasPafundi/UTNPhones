package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Services.StateService;
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
    public List<State> GetAll(){
        try{
            return stateService.GetAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public State GetById(@PathVariable Integer id){
        try{
            return stateService.GetById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer Add(@RequestBody State state){
        try{
            return stateService.Add(state);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void Update(@RequestBody State state) throws Exception {
        try{
            stateService.Update(state);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            stateService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
