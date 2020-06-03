package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.Bill;
import com.utn.TPFinal.model.entities.User;
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

    @GetMapping("/User/{id}")
    public ResponseEntity<List<Bill>> getByUserID(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id) throws UserNotexistException {
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && (user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId() == id)){
                List<Bill> bills = billService.getByUserID(id);
                return (bills.size() > 0) ? ResponseEntity.ok(bills) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
