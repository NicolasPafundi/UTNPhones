package com.utn.TPFinal.controllers;

import com.utn.TPFinal.domain.Entities.*;
import com.utn.TPFinal.services.UserService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/User")
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public UserController(UserService userService,SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().equals("Empleado")){
                return ResponseEntity.ok(userService.getAll());
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        try{
            return userService.getById(id);
        }catch (Exception ex){
           throw ex;
        }
    }

    @PostMapping("/")
    public Integer add(@RequestBody User user){
        try{
            return userService.add(user);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void update(@RequestBody User user) throws Exception {
        try{
            userService.update(user);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        try{
            userService.remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
