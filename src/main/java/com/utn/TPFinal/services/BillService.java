package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.entities.Bill;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.repositories.IBillRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final IBillRepository billRepository;
    private final IUserRepository userRepository;

    @Autowired
    public BillService(IBillRepository billRepository, IUserRepository userRepository)
    {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    public List<Bill> getAll(){
        try{
            return billRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<Bill> getByUserID(Integer id) throws UserNotexistException {

        User user = userRepository.findById(id).orElseThrow(()->new UserNotexistException());
        return billRepository.findByUserId(id);
    }

    public Bill getById(Integer Id){
        try{
            return billRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int add(Bill bill){
        try{
            return billRepository.save(bill).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void remove(Integer Id){
        try{
            billRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void update(Bill bill) throws ValidationException, Exception {
        try {
            if (billRepository.existsById(bill.getId())) {
                billRepository.save(bill);
            } else {
                throw new ValidationException("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }

}
