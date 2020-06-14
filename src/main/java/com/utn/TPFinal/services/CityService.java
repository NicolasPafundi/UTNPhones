package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.repositories.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final ICityRepository cityRepository;

    @Autowired
    public CityService(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllByState(Integer stateId){
        try{
            return cityRepository.getAllByState(stateId);
        }catch(Exception ex){
            throw ex;
        }
    }

    public City getById(Integer Id){
        try{
            return cityRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(City city){
        try{
            return cityRepository.save(city).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            cityRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(City city) throws ValidationException, Exception {
        try {
            if (cityRepository.existsById(city.getId())) {
                cityRepository.save(city);
            } else {
                throw new ValidationException("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }

}
