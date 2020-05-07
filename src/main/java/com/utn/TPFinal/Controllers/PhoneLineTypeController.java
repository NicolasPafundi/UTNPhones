package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Services.PhoneLineTypeService;
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
    public List<PhoneLineType> GetAll(){
        try{
            return phoneLineTypeService.GetAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public PhoneLineType GetById(@PathVariable Integer id){
        try{
            return phoneLineTypeService.GetById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer Add(@RequestBody PhoneLineType phoneLineType){
        try{
            return phoneLineTypeService.Add(phoneLineType);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void Update(@RequestBody PhoneLineType phoneLineType) throws Exception {
        try{
            phoneLineTypeService.Update(phoneLineType);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            phoneLineTypeService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
