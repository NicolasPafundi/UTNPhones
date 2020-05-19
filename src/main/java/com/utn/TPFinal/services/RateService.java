package com.utn.TPFinal.services;

import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.repositories.IRateRepository;
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

    public List<Rate> getAll(){
        try{
            return rateRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public Rate getById(Integer Id){
        try{
            return rateRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(Rate rate){
        try{
            return rateRepository.save(rate).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            rateRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(Rate rate) throws Exception {
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

