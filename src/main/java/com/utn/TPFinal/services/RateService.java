package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourseNoExistExeption;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.model.projections.RatesReport;
import com.utn.TPFinal.repositories.ICityRepository;
import com.utn.TPFinal.repositories.IRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

import static jdk.internal.dynalink.support.Guards.isNotNull;
import static jdk.internal.dynalink.support.Guards.isNull;

@Service
public class RateService {
    private final IRateRepository rateRepository;
    private final ICityRepository cityRepository;

    @Autowired
    public RateService(IRateRepository rateRepository, ICityRepository cityRepository) {
        this.rateRepository = rateRepository;
        this.cityRepository = cityRepository;
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

    public List<RatesReport> getRatesBetweenAreaCodes(Integer areaCodeFrom, Integer areaCodeTo) throws ResourseNoExistExeption {
       try{
           City cityFrom = cityRepository.findByAreaCode(areaCodeFrom);
           if (!Objects.isNull(areaCodeTo)){ City cityTo = cityRepository.findByAreaCode(areaCodeTo); }
           return rateRepository.getRatesBetweenAreaCodes(areaCodeFrom,areaCodeTo);

       } catch (Exception ex) {
           throw new ResourseNoExistExeption();
       }


    }
}

