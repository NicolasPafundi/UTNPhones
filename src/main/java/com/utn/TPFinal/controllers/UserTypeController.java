package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.User;
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


    @GetMapping("/")
    public ResponseEntity<List<UserType>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                List<UserType> userTypes = userTypeService.getAll();
                return (userTypes.size() > 0) ? ResponseEntity.ok(userTypes) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                UserType userType = userTypeService.getById(id);
                return (userType != null) ? ResponseEntity.ok(userType) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody UserType userType){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                return ResponseEntity.status(HttpStatus.CREATED).body(userTypeService.add(userType));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody UserType userType) throws Exception {
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                userTypeService.update(userType);
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
                userTypeService.remove(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
