package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.Entities.State;
import com.utn.TPFinal.Repositories.Contracts.IStateRepository;
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

    public List<State> GetAll(){
        try{
            return stateRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public State GetById(Integer Id){
        try{
            return stateRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int Add(State state){
        try{
            return stateRepository.save(state).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Remove(Integer Id){
        try{
            stateRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Update(State state) throws Exception {
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
