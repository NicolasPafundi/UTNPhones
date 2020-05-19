package com.utn.TPFinal.controllers;

import com.utn.TPFinal.domain.Entities.*;
import com.utn.TPFinal.services.RateService;
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
    public List<Rate> getAll(){
        try{
            return rateService.getAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public Rate getById(@PathVariable Integer id){
        try{
            return rateService.getById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer add(@RequestBody Rate rate){
        try{
            return rateService.add(rate);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void update(@RequestBody Rate rate) throws Exception {
        try{
            rateService.update(rate);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        try{
            rateService.remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
