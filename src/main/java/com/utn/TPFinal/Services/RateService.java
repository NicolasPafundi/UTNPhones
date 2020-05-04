package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.Entities.Rate;
import com.utn.TPFinal.Repositories.Contracts.IRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RateService {
    private final IRateRepository rateRepository;

    @Autowired
    public RateService(IRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public List<Rate> GetAll(){
        try{
            return rateRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public Rate GetById(Integer Id){
        try{
            return rateRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int Add(Rate rate){
        try{
            return rateRepository.save(rate).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Remove(Integer Id){
        try{
            rateRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Update(Rate rate) throws Exception {
        try {
            if (rateRepository.existsById(rate.getId())) {
                rateRepository.save(rate);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}

