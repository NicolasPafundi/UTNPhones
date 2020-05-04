package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.Entities.City;
import com.utn.TPFinal.Repositories.Contracts.ICityRepository;
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

    public List<City> GetAll(){
        try{
            return cityRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public City GetById(Integer Id){
        try{
            return cityRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int Add(City city){
        try{
            return cityRepository.save(city).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Remove(Integer Id){
        try{
            cityRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Update(City city) throws Exception {
        try {
            if (cityRepository.existsById(city.getId())) {
                cityRepository.save(city);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}
