package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.services.UserTypeService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/api/UserType")
public class UserTypeController {

    private final UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping("/Employee/")
    public ResponseEntity<List<UserType>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<UserType> userTypes = userTypeService.getAll();
            return (userTypes.size() > 0) ? ResponseEntity.ok(userTypes) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
