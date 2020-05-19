package com.utn.TPFinal.services;

import com.utn.TPFinal.domain.Entities.PhoneLine;
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

    public int add(PhoneLine phoneLine){
        try{
            return phoneLineRepository.save(phoneLine).getId();
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

    public void update(PhoneLine phoneLine) throws Exception {
        try {
            if (phoneLineRepository.existsById(phoneLine.getId())) {
                phoneLineRepository.save(phoneLine);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}



