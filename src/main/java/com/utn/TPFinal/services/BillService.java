package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
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

    public List<Bill> getByUserID(Integer id) throws ResourceNotExistException {
        try{
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotExistException("User"));
        return billRepository.findByUserId(id);
        }catch(ResourceNotExistException ex){
            throw ex;
        }
    }

    public Bill getById(Integer Id) throws ResourceNotExistException, Exception {
        try{
            return billRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("Bill"));
        }catch(ResourceNotExistException ex){
            throw ex;
        }
    }

    public int add(Bill bill) throws Exception, ResourceAlreadyExistExeption {
        try{
            if(bill.getId()!=null && billRepository.existsById(bill.getId())){throw new ResourceAlreadyExistExeption("Bill");}
            return billRepository.save(bill).getId();
        }catch(ResourceAlreadyExistExeption ex){
            throw ex;
        }
    }

    public void remove(Integer Id) throws ResourceNotExistException, Exception {
        try{
            billRepository.findById(Id).orElseThrow(()->new ResourceNotExistException("Bill"));
            billRepository.deleteById(Id);
        }catch(ResourceNotExistException ex){
            throw ex;
        }
    }

    public void update(Bill bill) throws Exception, ResourceNotExistException {
        try {
            billRepository.findById(bill.getId()).orElseThrow(()->new ResourceNotExistException("Bill"));
            billRepository.save(bill);
        }catch(ResourceNotExistException ex){
            throw ex;
        }
    }

}
