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


    @GetMapping("/GetAll")
    public List<User> GetAll(){
        try{
            return userService.GetAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/GetById/{id}")
    public User GetById(@PathVariable Integer id){
        try{
            return userService.GetById(id);
        }catch (Exception ex){
           throw ex;
        }
    }

    @PostMapping("/Add")
    public Integer Add(@RequestBody User user){
        try{
            return userService.Add(user);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Update")
    public void Update(@RequestBody User user) throws Exception {
        try{
            userService.Update(user);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Remove/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            userService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
