package com.utn.TPFinal.services;

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

    public UserType getById(Integer Id){
        try{
            return userTypeRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(UserType userType){
        try{
            return userTypeRepository.save(userType).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            userTypeRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(UserType userType) throws Exception {
        try {
            if (userTypeRepository.existsById(userType.getId())) {
                userTypeRepository.save(userType);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}
