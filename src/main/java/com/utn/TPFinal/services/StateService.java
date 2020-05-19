package com.utn.TPFinal.services;

import com.utn.TPFinal.domain.Entities.State;
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

    public State getById(Integer Id){
        try{
            return stateRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(State state){
        try{
            return stateRepository.save(state).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            stateRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(State state) throws Exception {
        try {
            if (stateRepository.existsById(state.getId())) {
                stateRepository.save(state);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}
