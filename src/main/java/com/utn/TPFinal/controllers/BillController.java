package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.Bill;
import com.utn.TPFinal.services.BillService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Bills")
public class BillController {

    private final BillService billService;
    private final SessionManager sessionManager;

    @Autowired
    public BillController(BillService billService, SessionManager sessionManager){
        this.billService = billService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/Employee/User/{id}")
    public ResponseEntity<List<Bill>> getByUserID(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id) throws ResourceNotExistException {
        try{
            List<Bill> bills = billService.getByUserID(id);
            return (bills.size() > 0) ? ResponseEntity.ok(bills) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (ResourceNotExistException ex){
            throw ex;
        }
    }

    @GetMapping("/Client/me")
    public ResponseEntity<List<Bill>> getByCurrentUser(@RequestHeader("Authorization") String sessionToken) throws ResourceNotExistException {
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            List<Bill> bills = billService.getByUserID(userId);
            return (bills.size() > 0) ? ResponseEntity.ok(bills) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (ResourceNotExistException ex){
            throw ex;
        }
    }
}
