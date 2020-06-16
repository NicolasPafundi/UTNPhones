package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.repositories.IPhoneLineTypeRepository;
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

    public PhoneLineType getById(Integer Id) throws ResourceNotExistException, Exception {
        try{
            return phoneLineTypeRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("PhoneLineType"));
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(PhoneLineType phoneLineType) throws ResourceAlreadyExistExeption, Exception {
        try{
            if(phoneLineType.getId()!=null && phoneLineTypeRepository.existsById(phoneLineType.getId())){throw new ResourceAlreadyExistExeption("PhoneLineType");}
            return phoneLineTypeRepository.save(phoneLineType).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id) throws ResourceNotExistException, Exception {
        try{
            phoneLineTypeRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("PhoneLineType"));
            phoneLineTypeRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(PhoneLineType phoneLineType) throws Exception, ResourceNotExistException {
        try {
            phoneLineTypeRepository.findById(phoneLineType.getId()).orElseThrow(()->new ResourceNotExistException("PhoneLineType"));
            phoneLineTypeRepository.save(phoneLineType);

        }catch(Exception ex){
            throw ex;
        }
    }
}
