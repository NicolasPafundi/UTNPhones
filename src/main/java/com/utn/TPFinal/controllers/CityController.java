package com.utn.TPFinal.controllers;


import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.model.entities.User;
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

    @GetMapping("/State/{id}")
    public ResponseEntity<List<City>> getAllByState(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id){
        try{
            List<City> cities = cityService.getAllByState(id);
            return (cities.size() > 0) ? ResponseEntity.ok(cities) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                City city = cityService.getById(id);
                return (city != null) ? ResponseEntity.ok(city) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody City city){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                return ResponseEntity.status(HttpStatus.CREATED).body(cityService.add(city));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody City city) throws Exception {
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                cityService.update(city);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                cityService.remove(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
