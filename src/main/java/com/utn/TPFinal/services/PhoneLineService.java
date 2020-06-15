package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.PhoneLineTypes;
import com.utn.TPFinal.model.entities.PhoneLine;
import com.utn.TPFinal.repositories.IPhoneLineRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneLineService {
    private final IPhoneLineRepository phoneLineRepository;
    private  final IUserRepository userRepository;

    @Autowired
    public PhoneLineService(IPhoneLineRepository phoneLineRepository, IUserRepository userRepository) {
        this.phoneLineRepository = phoneLineRepository;
        this.userRepository = userRepository;
    }

    public List<PhoneLine> getAll(){
        try{
            return phoneLineRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public PhoneLine getById(Integer Id) throws ResourceNotExistException, Exception {
        try{
            return phoneLineRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("PhoneLine"));
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<PhoneLine> getByUser(Integer userId) throws ResourceNotExistException, Exception {
        try{
            userRepository.findById(userId).orElseThrow(()->new ResourceNotExistException("User"));
            return phoneLineRepository.getByUser(userId);
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(PhoneLine phoneLine) throws Exception, ValidationException, ResourceAlreadyExistExeption {
        try{
            if(phoneLineRepository.existsById(phoneLine.getId())){throw new ResourceAlreadyExistExeption("PhoneLine");}
            if(phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.LANDLINE) || phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.MOVIL)){
                return phoneLineRepository.save(phoneLine).getId();
            }else{
                throw new ValidationException("Invalid Type Name");
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id) throws ResourceNotExistException, Exception {
        try{
            phoneLineRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("PhoneLine"));
            phoneLineRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(PhoneLine phoneLine) throws ValidationException, Exception, ResourceNotExistException {
        try{
            phoneLineRepository.findById(phoneLine.getId()).orElseThrow(()->new ResourceNotExistException("PhoneLine"));
            if(phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.LANDLINE) || phoneLine.getPhoneLineType().getName().equals(PhoneLineTypes.MOVIL)){
                phoneLineRepository.save(phoneLine);
            }else{
                throw new ValidationException("Invalid Type Name");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}



