package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.repositories.IUserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserTypeService {
    private final IUserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeService(IUserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public List<UserType> getAll(){
        try{
            return userTypeRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public UserType getById(Integer Id) throws ResourceNotExistException, Exception {
        try{
            return userTypeRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("UserType"));
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(UserType userType) throws ResourceAlreadyExistExeption, Exception {
        try{
            if(userType.getId()!=null && userTypeRepository.existsById(userType.getId())){throw new ResourceAlreadyExistExeption("UserType");}
            return userTypeRepository.save(userType).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id) throws ResourceNotExistException, Exception {
        try{
            userTypeRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("UserType"));
            userTypeRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(UserType userType) throws Exception, ResourceNotExistException {
        try {
            userTypeRepository.findById(userType.getId()).orElseThrow(()->new ResourceNotExistException("UserType"));
            userTypeRepository.save(userType);
        }catch(Exception ex){
            throw ex;
        }
    }
}
