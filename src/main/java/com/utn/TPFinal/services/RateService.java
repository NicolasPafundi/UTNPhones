package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.model.projections.RatesReport;
import com.utn.TPFinal.repositories.ICityRepository;
import com.utn.TPFinal.repositories.IRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

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

    public Rate getById(Integer Id) throws ResourceNotExistException, Exception {
        try{
            return rateRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("Rate"));
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(Rate rate) throws ResourceAlreadyExistExeption, Exception {
        try{
            if(rateRepository.existsById(rate.getId())){throw new ResourceAlreadyExistExeption("Rate");}
            return rateRepository.save(rate).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id) throws ResourceNotExistException, Exception {
        try{
            rateRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("Rate"));
            rateRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(Rate rate) throws Exception, ResourceNotExistException {
        try {
            rateRepository.findById(rate.getId()).orElseThrow(()->new ResourceNotExistException("Rate"));
            rateRepository.save(rate);

        }catch(Exception ex){
            throw ex;
        }
    }

    public List<RatesReport> getRatesBetweenAreaCodes(Integer areaCodeFrom, Integer areaCodeTo) throws ResourceNotExistException, Exception {
       try{
           City cityFrom = cityRepository.findByAreaCode(areaCodeFrom);
           City cityTo = cityRepository.findByAreaCode(areaCodeTo);
           if(Objects.nonNull(cityFrom) && Objects.nonNull(cityTo)){
               return rateRepository.getRatesBetweenAreaCodes(areaCodeFrom,areaCodeTo);
           }else{
               throw new ResourceNotExistException("Area Code");
           }
       } catch (Exception ex) {
           throw ex;
       }
    }
}

