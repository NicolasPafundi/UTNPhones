package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Services.UserService;
import com.utn.TPFinal.Session.SessionManager;
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
    public ResponseEntity<List<User>> GetAll(@RequestHeader("Authorization") String sessionToken){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && user.getUserType().getName().equals("Empleado")){
                return ResponseEntity.ok(userService.GetAll());
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public User GetById(@PathVariable Integer id){
        try{
            return userService.GetById(id);
        }catch (Exception ex){
           throw ex;
        }
    }

    @PostMapping("/")
    public Integer Add(@RequestBody User user){
        try{
            return userService.Add(user);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/")
    public void Update(@RequestBody User user) throws Exception {
        try{
            userService.Update(user);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            userService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
