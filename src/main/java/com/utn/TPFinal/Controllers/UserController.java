package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public List<User> GetAll(){
        try{
            return userService.GetAll();
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
