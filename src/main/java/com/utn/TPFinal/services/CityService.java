package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.repositories.ICityRepository;
import com.utn.TPFinal.repositories.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final ICityRepository cityRepository;
    private final IStateRepository stateRepository;

    @Autowired
    public CityService(ICityRepository cityRepository,IStateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public List<City> getAllByState(Integer stateId) throws ResourceNotExistException, Exception {
        try{
            stateRepository.findById(stateId).orElseThrow(()->new ResourceNotExistException("State"));
            return cityRepository.getAllByState(stateId);
        }catch(Exception ex){
            throw ex;
        }
    }

}
