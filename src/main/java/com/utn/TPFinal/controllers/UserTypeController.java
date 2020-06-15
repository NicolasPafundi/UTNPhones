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
    private final SessionManager sessionManager;

    @Autowired
    public UserTypeController(UserTypeService userTypeService, SessionManager sessionManager) {
        this.userTypeService = userTypeService;
        this.sessionManager = sessionManager;
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

    @GetMapping("/Employee/{id}")
    public ResponseEntity<UserType> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            UserType userType = userTypeService.getById(id);
            return (userType != null) ? ResponseEntity.ok(userType) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody UserType userType) throws ResourceAlreadyExistExeption, Exception {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userTypeService.add(userType));
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/Employee/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody UserType userType) throws Exception, ValidationException, ResourceNotExistException {
        try{
            userTypeService.update(userType);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            userTypeService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
