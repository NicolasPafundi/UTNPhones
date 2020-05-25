package com.utn.TPFinal.services;

import com.utn.TPFinal.model.dtos.LoginInput;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        try{
            return userRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<User> getAllByUserType(Integer userTypeId){
        try{
            return userRepository.getAllByUserType(userTypeId);
        }catch(Exception ex){
            throw ex;
        }
    }

    public User getById(Integer Id){
        try{
            return userRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public User getByUserNameAndPassword(LoginInput loginInput) throws UserNotexistException, ValidationException {
        if ((loginInput.getUserName() != null) && (loginInput.getPassword() != null)) {
            User user= userRepository.getByUserNameAndPassword(loginInput.getUserName(), loginInput.getPassword());
            return Optional.ofNullable(user).orElseThrow(() -> new UserNotexistException());
        } else {
            throw new ValidationException("username and password must have a value");
        }
    }

    public int add(User user){
        try{
            return userRepository.save(user).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            userRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(User user) throws Exception {
        try {
            if (userRepository.existsById(user.getId())) {
                userRepository.save(user);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}
