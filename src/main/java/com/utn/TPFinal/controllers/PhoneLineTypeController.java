package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.services.PhoneLineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/PhoneLineType")
public class PhoneLineTypeController {

    private final PhoneLineTypeService phoneLineTypeService;

    @Autowired
    public PhoneLineTypeController(PhoneLineTypeService phoneLineTypeService) {
        this.phoneLineTypeService = phoneLineTypeService;
    }


    @GetMapping("/")
    public List<PhoneLineType> getAll(){
        try{
            return phoneLineTypeService.getAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public PhoneLineType getById(@PathVariable Integer id){
        try{
            return phoneLineTypeService.getById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer add(@RequestBody PhoneLineType phoneLineType){
        try{
            return phoneLineTypeService.add(phoneLineType);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void update(@RequestBody PhoneLineType phoneLineType) throws Exception {
        try{
            phoneLineTypeService.update(phoneLineType);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        try{
            phoneLineTypeService.remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
