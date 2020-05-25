package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.services.RateService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/api/Rate")
public class RateController {

    private final RateService rateService;
    private final SessionManager sessionManager;

    @Autowired
    public RateController(RateService rateService, SessionManager sessionManager) {
        this.rateService = rateService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/")
    public ResponseEntity<List<Rate>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                List<Rate> rates = rateService.getAll();
                return (rates.size() > 0) ? ResponseEntity.ok(rates) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rate> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                Rate rate = rateService.getById(id);
                return (rate != null) ? ResponseEntity.ok(rate) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody Rate rate){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                return ResponseEntity.status(HttpStatus.CREATED).body(rateService.add(rate));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody Rate rate) throws Exception {
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                rateService.update(rate);
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
                rateService.remove(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
