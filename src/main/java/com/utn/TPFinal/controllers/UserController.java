package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.services.UserService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/api/User")
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public UserController(UserService userService,SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/Employee/")
    public ResponseEntity<List<User>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<User> users = userService.getAll();
            return (users.size() > 0) ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/UserType/{id}")
    public ResponseEntity<List<User>> getAllByUserType(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id){
        try{
            List<User> users = userService.getAllByUserType(id);
            return (users.size() > 0) ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<User> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User userResult = userService.getById(id);
            return (userResult != null) ? ResponseEntity.ok(userResult) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
           throw ex;
        }
    }

    @GetMapping("/Client/me")
    public ResponseEntity<User> getByCurrentUser(@RequestHeader("Authorization") String sessionToken){
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            User userResult = userService.getById(userId);
            return (userResult != null) ? ResponseEntity.ok(userResult) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody User newUser) throws ValidationException, Exception {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(newUser));
        }catch (Exception | ValidationException ex){
            throw ex;
        }
    }

    @PutMapping("/Employee/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody User userUpdate) throws Exception, ValidationException {
        try{
            userService.update(userUpdate);
            return ResponseEntity.ok().build();
        }catch (Exception | ValidationException ex){
            throw ex;
        }
    }

    @PutMapping("/Client/me")
    public ResponseEntity updateCurrentUser(@RequestHeader("Authorization") String sessionToken,@RequestBody User userUpdate) throws Exception, ValidationException {
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            if(userUpdate.getId() == userId){
                userService.update(userUpdate);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception | ValidationException ex){
            throw ex;
        }
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            userService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/Client/me")
    public ResponseEntity removeCurrentUser(@RequestHeader("Authorization") String sessionToken){
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            userService.remove(userId);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
