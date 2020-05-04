package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.Entities.UserType;
import com.utn.TPFinal.Repositories.Contracts.IUserTypeRepository;
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

    public List<UserType> GetAll(){
        try{
            return userTypeRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public UserType GetById(Integer Id){
        try{
            return userTypeRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int Add(UserType userType){
        try{
            return userTypeRepository.save(userType).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Remove(Integer Id){
        try{
            userTypeRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Update(UserType userType) throws Exception {
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
