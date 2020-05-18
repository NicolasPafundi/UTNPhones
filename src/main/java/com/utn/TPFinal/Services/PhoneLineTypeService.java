package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.Entities.PhoneLineType;
import com.utn.TPFinal.Repositories.Contracts.IPhoneLineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhoneLineTypeService {
    private final IPhoneLineTypeRepository phoneLineTypeRepository;

    @Autowired
    public PhoneLineTypeService(IPhoneLineTypeRepository phoneLineTypeRepository) {
        this.phoneLineTypeRepository = phoneLineTypeRepository;
    }

    public List<PhoneLineType> getAll(){
        try{
            return phoneLineTypeRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public PhoneLineType getById(Integer Id){
        try{
            return phoneLineTypeRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(PhoneLineType phoneLineType){
        try{
            return phoneLineTypeRepository.save(phoneLineType).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            phoneLineTypeRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(PhoneLineType phoneLineType) throws Exception {
        try {
            if (phoneLineTypeRepository.existsById(phoneLineType.getId())) {
                phoneLineTypeRepository.save(phoneLineType);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}
