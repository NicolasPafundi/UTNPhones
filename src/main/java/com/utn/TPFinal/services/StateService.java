package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.State;
import com.utn.TPFinal.repositories.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.TPFinal.exceptions.ValidationException;

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

    public State getById(Integer Id) throws ResourceNotExistException, Exception {
        try{
            return stateRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("State"));
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(State state) throws ResourceAlreadyExistExeption, Exception {
        try{
            if(stateRepository.existsById(state.getId())){throw new ResourceAlreadyExistExeption("State");}
            return stateRepository.save(state).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id) throws ResourceNotExistException, Exception {
        try{
            stateRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("State"));
            stateRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(State state) throws Exception, ResourceNotExistException {
        try {
            stateRepository.findById(state.getId()).orElseThrow(()->new ResourceNotExistException("State"));
            stateRepository.save(state);
        }catch(Exception ex){
            throw ex;
        }
    }
}
