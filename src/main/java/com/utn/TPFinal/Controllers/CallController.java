package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.DTOs.CallFilter;
import com.utn.TPFinal.Domain.DTOs.CallInput;
import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Domain.Projections.UserCall;
import com.utn.TPFinal.Services.CallService;
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

    @PostMapping("/getByUser")
    public List<UserCall> getByUser(@RequestBody CallFilter callFilter){
        try{
          return callService.getByUser(callFilter);
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
