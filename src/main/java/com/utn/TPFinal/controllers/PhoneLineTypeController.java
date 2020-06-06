package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.model.entities.User;
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
    private final SessionManager sessionManager;

    @Autowired
    public PhoneLineTypeController(PhoneLineTypeService phoneLineTypeService, SessionManager sessionManager) {
        this.phoneLineTypeService = phoneLineTypeService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/Client/")
    public ResponseEntity<List<PhoneLineType>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<PhoneLineType> phoneLineTypes = phoneLineTypeService.getAll();
            return (phoneLineTypes.size() > 0) ? ResponseEntity.ok(phoneLineTypes) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<PhoneLineType> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            PhoneLineType phoneLineType = phoneLineTypeService.getById(id);
            return (phoneLineType != null) ? ResponseEntity.ok(phoneLineType) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody PhoneLineType phoneLineType){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(phoneLineTypeService.add(phoneLineType));
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/Employee/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody PhoneLineType phoneLineType) throws Exception {
        try{
            phoneLineTypeService.update(phoneLineType);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            phoneLineTypeService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
