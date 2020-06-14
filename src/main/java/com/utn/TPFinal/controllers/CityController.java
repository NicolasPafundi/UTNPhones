package com.utn.TPFinal.controllers;


import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.services.CityService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("")
@RequestMapping("/api/City")
public class CityController {

    private final CityService cityService;
    private final SessionManager sessionManager;

    @Autowired
    public CityController(CityService cityService, SessionManager sessionManager) {
        this.cityService = cityService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/Employee/State/{id}")
    public ResponseEntity<List<City>> getAllByState(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id){
        try{
            List<City> cities = cityService.getAllByState(id);
            return (cities.size() > 0) ? ResponseEntity.ok(cities) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<City> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            City city = cityService.getById(id);
            return (city != null) ? ResponseEntity.ok(city) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody City city){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(cityService.add(city));
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/Employee/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody City city) throws Exception, ValidationException {
        try{
            cityService.update(city);
            return ResponseEntity.ok().build();
        }catch (Exception | ValidationException ex){
            throw ex;
        }
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            cityService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
