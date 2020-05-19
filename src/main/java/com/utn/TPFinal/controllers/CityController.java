package com.utn.TPFinal.controllers;

import com.utn.TPFinal.domain.Entities.*;
import com.utn.TPFinal.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/City")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/")
    public List<City> getAll(){
        try{
            return cityService.getAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable Integer id){
        try{
            return cityService.getById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public Integer add(@RequestBody City city){
        try{
            return cityService.add(city);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void update(@RequestBody City city) throws Exception {
        try{
            cityService.update(city);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        try{
            cityService.remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
