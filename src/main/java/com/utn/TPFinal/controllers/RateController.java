package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.model.projections.RatesReport;
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


    @GetMapping("/Employee/")
    public ResponseEntity<List<Rate>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<Rate> rates = rateService.getAll();
            return (rates.size() > 0) ? ResponseEntity.ok(rates) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<Rate> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            Rate rate = rateService.getById(id);
            return (rate != null) ? ResponseEntity.ok(rate) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody Rate rate) throws ResourceAlreadyExistExeption, Exception {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(rateService.add(rate));
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/Employee/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody Rate rate) throws Exception, ValidationException, ResourceNotExistException {
        try{
            rateService.update(rate);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            rateService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/ratesBetweenAreaCodes/{areaCodeFrom}/{areaCodeTo}")
    public ResponseEntity<List<RatesReport>> getRatesBetweenAreaCodes(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer areaCodeFrom, @PathVariable Integer areaCodeTo) throws ResourceNotExistException, Exception {
        try{
            if(areaCodeTo == 0) {
                areaCodeTo = null;
            }
            List<RatesReport> report = rateService.getRatesBetweenAreaCodes(areaCodeFrom,areaCodeTo);
            return (report.size() > 0)? ResponseEntity.ok(report) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }catch (Exception ex){
            throw ex;
        }
    }


}
