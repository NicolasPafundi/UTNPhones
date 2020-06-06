package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.PhoneLine;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.services.PhoneLineService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/api/PhoneLine")
public class PhoneLineController {

    private final PhoneLineService phoneLineService;
    private final SessionManager sessionManager;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService, SessionManager sessionManager) {
        this.phoneLineService = phoneLineService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/Employee/")
    public ResponseEntity<List<PhoneLine>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<PhoneLine> phoneLines = phoneLineService.getAll();
            return (phoneLines.size() > 0) ? ResponseEntity.ok(phoneLines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<PhoneLine> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            PhoneLine phoneLine = phoneLineService.getById(id);
            return (phoneLine != null) ? ResponseEntity.ok(phoneLine) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/User/{id}")
    public ResponseEntity<List<PhoneLine>> getByUserId(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id){
        try{
            List<PhoneLine> phoneLines = phoneLineService.getByUser(id);
            return (phoneLines.size() > 0) ? ResponseEntity.ok(phoneLines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Client/CurrentUser")
    public ResponseEntity<List<PhoneLine>> getByCurrentUser(@RequestHeader("Authorization") String sessionToken){
        try{
            Integer userId= sessionManager.getCurrentUser(sessionToken).getId();
            List<PhoneLine> phoneLines = phoneLineService.getByUser(userId);
            return (phoneLines.size() > 0) ? ResponseEntity.ok(phoneLines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody PhoneLine phoneLine){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(phoneLineService.add(phoneLine));
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/Employee/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody PhoneLine phoneLine) throws Exception {
        try{
            phoneLineService.update(phoneLine);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            phoneLineService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
