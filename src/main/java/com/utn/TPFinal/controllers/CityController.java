package com.utn.TPFinal.controllers;


import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
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
    public ResponseEntity<List<City>> getAllByState(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            List<City> cities = cityService.getAllByState(id);
            return (cities.size() > 0) ? ResponseEntity.ok(cities) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (ResourceNotExistException ex){
            throw ex;
        }
    }
}
