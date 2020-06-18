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
}
