package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/Rate")
public class RateController {

    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }


    @GetMapping("/")
    public List<Rate> GetAll(){
        try{
            return rateService.GetAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public Rate GetById(@PathVariable Integer id){
        try{
            return rateService.GetById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer Add(@RequestBody Rate rate){
        try{
            return rateService.Add(rate);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void Update(@RequestBody Rate rate) throws Exception {
        try{
            rateService.Update(rate);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            rateService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
