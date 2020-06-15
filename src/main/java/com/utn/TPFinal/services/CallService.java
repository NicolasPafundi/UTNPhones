package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.dtos.CallInput;
import com.utn.TPFinal.model.dtos.CallsReportFilter;
import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.InfraResponse;
import com.utn.TPFinal.model.projections.ReportCallsByUserByDate;
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

    public Call getById(Integer Id) throws ResourceNotExistException {
            return callRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("Call"));
    }

    public InfraResponse createCall(CallInput call) {
        try {
          return callRepository.createCall(call.getNumberFrom(), call.getNumberTo(), call.getDuration(), call.getCallDate());
        }catch (Exception ex){
            throw ex;
        }

    }

    public void remove(Integer Id) throws ResourceNotExistException{
        callRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("Call"));
        callRepository.deleteById(Id);
    }

    public void update(Call call) throws Exception, ResourceNotExistException {
        try {
            callRepository.findById(call.getId()).orElseThrow(()->new ResourceNotExistException("Call"));
            callRepository.save(call);
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<Call> getByUserId(Integer id) throws ResourceNotExistException {

        userRepository.findById(id).orElseThrow(()->new ResourceNotExistException("User"));
        return callRepository.getByUserId(id);
    }

    public List<ReportCallsByUserByDate> getReportCallsByUserByDate(CallsReportFilter callsReportFilter) throws ResourceNotExistException {
        userRepository.findById(callsReportFilter.getUserId()).orElseThrow(()->new ResourceNotExistException("User"));
        return callRepository.getReportCallsByUserByDate(callsReportFilter.getUserId(),callsReportFilter.getDateFrom(),callsReportFilter.getDateTo());
    }
}