package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.DTOs.CallFilter;
import com.utn.TPFinal.Domain.Entities.Call;
import com.utn.TPFinal.Domain.Projections.UserCall;
import com.utn.TPFinal.Repositories.Contracts.ICallRepository;
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

    public List<Call> GetAll(){
        try{
            return callRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public Call GetById(Integer Id){
        try{
            return callRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int Add(Call call){
        try{
            return callRepository.save(call).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Remove(Integer Id){
        try{
            callRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Update(Call call) throws Exception {
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

    public List<UserCall> GetByUser(CallFilter callFilter)
    {
        try{
            return callRepository.GetByUser(callFilter.getDateFrom(),callFilter.getDatoTo(),callFilter.getUserId());
        }catch(Exception ex){
            throw ex;
        }
    }
}