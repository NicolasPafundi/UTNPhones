package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.Enum.UserTypeEnum;
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


    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                List<User> users = userService.getAll();
                return (users.size() > 0) ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/UserType/{id}")
    public ResponseEntity<List<User>> getAllByUserType(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                List<User> users = userService.getAllByUserType(id);
                return (users.size() > 0) ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && (user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId() == id)){
                User userResult = userService.getById(id);
                return (userResult != null) ? ResponseEntity.ok(userResult) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
           throw ex;
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> add(@RequestHeader("Authorization") String sessionToken,@RequestBody User newUser){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name())){
                return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(newUser));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody User userUpdate) throws Exception {
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && (user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId() == userUpdate.getId())){
                userService.update(userUpdate);
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

            if(user!=null && (user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId() == id)){
                userService.remove(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
