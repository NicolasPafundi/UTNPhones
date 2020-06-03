package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.PhoneLine;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
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

    @GetMapping("/")
    public ResponseEntity<List<PhoneLine>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                List<PhoneLine> phoneLines = phoneLineService.getAll();
                return (phoneLines.size() > 0) ? ResponseEntity.ok(phoneLines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneLine> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                PhoneLine phoneLine = phoneLineService.getById(id);
                return (phoneLine != null) ? ResponseEntity.ok(phoneLine) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/GetCantCellphoneLines")
    public ResponseEntity<Integer> getCantCellphoneLines(@RequestHeader("Authorization") String sessionToken) {
        try{
            Integer cellphoneLines = phoneLineService.getCantCellphoneLines();
            return ResponseEntity.ok(cellphoneLines);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/User/{id}")
    public ResponseEntity<List<PhoneLine>> getByUserId(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId()==id){
                List<PhoneLine> phoneLines = phoneLineService.getByUser(id);
                return (phoneLines.size() > 0) ? ResponseEntity.ok(phoneLines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody PhoneLine phoneLine){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                return ResponseEntity.status(HttpStatus.CREATED).body(phoneLineService.add(phoneLine));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody PhoneLine phoneLine) throws Exception {
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                phoneLineService.update(phoneLine);
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
                phoneLineService.remove(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
