package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.Entities.*;
import com.utn.TPFinal.Services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("")
@RequestMapping("/UserType")
public class UserTypeController {

    private final UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }


    @GetMapping("/GetAll")
    public List<UserType> GetAll(){
        try{
            return userTypeService.GetAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/GetById/{id}")
    public UserType GetById(@PathVariable Integer id){
        try{
            UserType user= new UserType();
            return user;
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Add")
    public Integer Add(@RequestBody UserType userType){
        try{
            return userTypeService.Add(userType);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Update")
    public void Update(@RequestBody UserType userType) throws Exception {
        try{
            userTypeService.Update(userType);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Remove/{id}")
    public void Remove(@PathVariable Integer id){
        try{
            userTypeService.Remove(id);
        }catch (Exception ex){
            throw ex;
        }
    }
}
