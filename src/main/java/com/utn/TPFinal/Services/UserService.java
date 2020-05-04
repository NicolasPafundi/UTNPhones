package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.Entities.User;
import com.utn.TPFinal.Repositories.Contracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> GetAll(){
        try{
            return userRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public User GetById(Integer Id){
        try{
            return userRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int Add(User user){
        try{
            return userRepository.save(user).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Remove(Integer Id){
        try{
            userRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Update(User user) throws Exception {
        try {
            if (userRepository.existsById(user.getId())) {
                userRepository.save(user);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }
}
