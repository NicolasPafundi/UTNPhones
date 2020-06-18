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
}
