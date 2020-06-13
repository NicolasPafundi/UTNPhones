package com.utn.TPFinal.services;


import com.sun.istack.Nullable;
import com.utn.TPFinal.exceptions.ResourseNoExistExeption;
import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.dtos.CallInput;
import com.utn.TPFinal.model.dtos.CallsReportFilter;
import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.InfraResponse;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.model.projections.ReportCallsByUserByDate;
import com.utn.TPFinal.repositories.ICallRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Call getById(Integer Id) throws ResourseNoExistExeption {

            return callRepository.findById(Id).orElseThrow(()->new ResourseNoExistExeption());
    }

    public InfraResponse createCall(CallInput call) {
        try {
          return callRepository.createCall(call.getNumberFrom(), call.getNumberTo(), call.getDuration(), call.getCallDate());

        }catch (Exception ex){
            throw ex;
        }

    }

    public void remove(Integer Id) throws ResourseNoExistExeption{
        Call call = callRepository.findById(Id).orElseThrow(()->new ResourseNoExistExeption());
        callRepository.deleteById(Id);

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

    public List<Call> getByUserId(Integer id) throws ResourseNoExistExeption {

        User user = userRepository.findById(id).orElseThrow(()->new ResourseNoExistExeption());
        return callRepository.getByUserId(id);
    }

    public List<ReportCallsByUserByDate> getReportCallsByUserByDate(CallsReportFilter callsReportFilter) throws ResourseNoExistExeption {
        User user = userRepository.findById(callsReportFilter.getUserId()).orElseThrow(()->new ResourseNoExistExeption());
        return callRepository.getReportCallsByUserByDate(callsReportFilter.getUserId(),callsReportFilter.getDateFrom(),callsReportFilter.getDateTo());
    }
}