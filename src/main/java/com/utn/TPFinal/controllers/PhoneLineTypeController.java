package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.services.PhoneLineTypeService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/api/PhoneLineType")
public class PhoneLineTypeController {

    private final PhoneLineTypeService phoneLineTypeService;

    @Autowired
    public PhoneLineTypeController(PhoneLineTypeService phoneLineTypeService) {
        this.phoneLineTypeService = phoneLineTypeService;
    }


    @GetMapping("/Employee/")
    public ResponseEntity<List<PhoneLineType>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<PhoneLineType> phoneLineTypes = phoneLineTypeService.getAll();
            return (phoneLineTypes.size() > 0) ? ResponseEntity.ok(phoneLineTypes) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
