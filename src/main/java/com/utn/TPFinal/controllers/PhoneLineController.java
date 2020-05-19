package com.utn.TPFinal.controllers;

import com.utn.TPFinal.domain.Entities.*;
import com.utn.TPFinal.services.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/PhoneLine")
public class PhoneLineController {

    private final PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }


    @GetMapping("/")
    public List<PhoneLine> getAll(){
        try{
            return phoneLineService.getAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public PhoneLine getById(@PathVariable Integer id){
        try{
            return phoneLineService.getById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer add(@RequestBody PhoneLine phoneLine){
        try{
            return phoneLineService.add(phoneLine);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void update(@RequestBody PhoneLine phoneLine) throws Exception {
        try{
            phoneLineService.update(phoneLine);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        try{
            phoneLineService.remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
