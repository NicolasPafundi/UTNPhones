package com.utn.TPFinal.services;

import com.utn.TPFinal.domain.DTOs.CallFilter;
import com.utn.TPFinal.domain.Entities.Call;
import com.utn.TPFinal.domain.Projections.UserCall;
import com.utn.TPFinal.repositories.ICallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {
    private final ICallRepository callRepository;
    private CallFilter callFilter;

    @Autowired
    public CallService(ICallRepository callRepository) {
        this.callRepository = callRepository;
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

    public List<UserCall> getByUser(CallFilter callFilter)
    {
        try{
            return callRepository.getByUser(callFilter.getDateFrom(),callFilter.getDateTo(),callFilter.getUserId());
        }catch(Exception ex){
            throw ex;
        }
    }
}