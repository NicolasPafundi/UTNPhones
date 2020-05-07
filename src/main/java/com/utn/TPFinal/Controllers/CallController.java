package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.DTOs.CallInput;
import com.utn.TPFinal.Domain.Entities.*;
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
    public List<Call> GetAll(){
        try{
            return callService.GetAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public Call GetById(@PathVariable Integer id){
        try{
            return callService.GetById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer New(@RequestBody CallInput call){
        try{
            return 1;
            //return callService.New(call);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void Update(@RequestBody Call call) throws Exception {
        try{
            callService.Update(call);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            callService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
