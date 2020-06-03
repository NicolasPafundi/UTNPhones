package com.utn.TPFinal.services;


import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.repositories.ICallRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {
    private final ICallRepository callRepository;
    private final IUserRepository userRepository;

    @Autowired
    public CallService(ICallRepository callRepository, IUserRepository userRepository) {
        this.callRepository = callRepository;
        this.userRepository = userRepository;
    }

    public List<Call> getAll(){
        try{
            return callRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public Call getById(Integer Id){
        try{
            return callRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(Call call){
        try{
            return callRepository.save(call).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            callRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(Call call) throws Exception {
        try {
            if (callRepository.existsById(call.getId())) {
                callRepository.save(call);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<Call> getByUserId(Integer id) {
        try{
            return callRepository.getByUserId(id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public Call getLastCallByDni(String dni) {
        try {
            User user = userRepository.findByDni(dni);
            if (user != null) {
               return callRepository.getLastCallByUserId(user.getDni());
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }
}