package com.utn.TPFinal.services;

import com.utn.TPFinal.model.entities.State;
import com.utn.TPFinal.repositories.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private final IStateRepository stateRepository;

    @Autowired
    public StateService(IStateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<State> getAll(){
        try{
            return stateRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }
}
