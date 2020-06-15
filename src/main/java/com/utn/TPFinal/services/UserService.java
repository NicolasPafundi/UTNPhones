package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.InvalidLoginException;
import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.dtos.LoginInput;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.repositories.IUserRepository;
import com.utn.TPFinal.repositories.IUserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final IUserTypeRepository userTypeRepository;

    @Autowired
    public UserService(IUserRepository userRepository,IUserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    public List<User> getAll(){
        try{
            return userRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<User> getAllByUserType(Integer userTypeId) throws ResourceNotExistException, Exception {
        try{
            userTypeRepository.findById(userTypeId).orElseThrow(()->new ResourceNotExistException("UserType"));
            return userRepository.getAllByUserType(userTypeId);
        }catch(Exception ex){
            throw ex;
        }
    }

    public User getById(Integer Id) throws ResourceNotExistException, Exception {
        try{
            return userRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("User"));
        }catch(Exception ex){
            throw ex;
        }
    }

    public User getByUserNameAndPassword(LoginInput loginInput) throws ValidationException, InvalidLoginException {
        if ((loginInput.getUserName() != null) && (loginInput.getPassword() != null)) {
            User user= userRepository.getByUserNameAndPassword(loginInput.getUserName(), loginInput.getPassword());
            return Optional.ofNullable(user).orElseThrow(() -> new InvalidLoginException());
        } else {
            throw new ValidationException("username and password must have a value");
        }
    }

    public int add(User user) throws ValidationException, Exception, ResourceAlreadyExistExeption {
        try{
            if(userRepository.existsById(user.getId())){throw new ResourceAlreadyExistExeption("User");}
            if(user.getUserType().getName().equals(UserTypes.CLIENT) || user.getUserType().getName().equals(UserTypes.EMPLOYEE)|| user.getUserType().getName().equals(UserTypes.INFRASTRUCTURE)){
                return userRepository.save(user).getId();
            }else{
                throw new ValidationException("Invalid Type Name");
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id) throws ResourceNotExistException, Exception {
        try{
            userRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("User"));
            userRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(User user) throws ValidationException, Exception, ResourceNotExistException {
        try {
            userRepository.findById(user.getId()).orElseThrow(()->new ResourceNotExistException("User"));
            if(user.getUserType().getName().equals(UserTypes.CLIENT) || user.getUserType().getName().equals(UserTypes.EMPLOYEE)|| user.getUserType().getName().equals(UserTypes.INFRASTRUCTURE)){
                userRepository.save(user);
            }else{
                throw new ValidationException("Invalid Type Name");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}
