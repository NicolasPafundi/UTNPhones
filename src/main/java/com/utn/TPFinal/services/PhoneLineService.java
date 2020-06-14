package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.PhoneLineTypes;
import com.utn.TPFinal.model.entities.PhoneLine;
import com.utn.TPFinal.repositories.IPhoneLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneLineService {
    private final IPhoneLineRepository phoneLineRepository;

    @Autowired
    public PhoneLineService(IPhoneLineRepository phoneLineRepository) {
        this.phoneLineRepository = phoneLineRepository;
    }

    public List<PhoneLine> getAll(){
        try{
            return phoneLineRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public PhoneLine getById(Integer Id){
        try{
            return phoneLineRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<PhoneLine> getByUser(Integer userId){
        try{
            return phoneLineRepository.getByUser(userId);
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(PhoneLine phoneLine) throws Exception, ValidationException {
        try{
            if(phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.LANDLINE) || phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.MOVIL)){
                return phoneLineRepository.save(phoneLine).getId();
            }else{
                throw new ValidationException("Invalid Type Name");
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            phoneLineRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(PhoneLine phoneLine) throws ValidationException, Exception {
        try {
            if (phoneLineRepository.existsById(phoneLine.getId())) {
                if(phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.LANDLINE) || phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.MOVIL)){
                    phoneLineRepository.save(phoneLine);
                }else{
                    throw new ValidationException("Invalid Type Name");
                }
            } else {
                throw new ValidationException("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}



