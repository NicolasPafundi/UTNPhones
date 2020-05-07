package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Services.PhoneLineService;
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
    public List<PhoneLine> GetAll(){
        try{
            return phoneLineService.GetAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public PhoneLine GetById(@PathVariable Integer id){
        try{
            return phoneLineService.GetById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer Add(@RequestBody PhoneLine phoneLine){
        try{
            return phoneLineService.Add(phoneLine);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void Update(@RequestBody PhoneLine phoneLine) throws Exception {
        try{
            phoneLineService.Update(phoneLine);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            phoneLineService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
