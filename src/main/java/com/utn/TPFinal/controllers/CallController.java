package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.entities.*;
import com.utn.TPFinal.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("")
@RequestMapping("/Call")
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }


    @GetMapping("/")
    public List<Call> getAll(){
        try{
            return callService.getAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public Call getById(@PathVariable Integer id){
        try{
            return callService.getById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/User/{id}")
    public List<Call> getByUserId(@PathVariable Integer id){
        try{
            return callService.getByUserId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer add(@RequestBody Call call){
        try{
           return callService.add(call);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void update(@RequestBody Call call) throws Exception {
        try{
            callService.update(call);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        try{
            callService.remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
