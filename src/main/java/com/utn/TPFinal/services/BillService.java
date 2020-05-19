package com.utn.TPFinal.services;

import com.utn.TPFinal.domain.Entities.Bill;
import com.utn.TPFinal.repositories.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillService {
    private final IBillRepository billRepository;

    @Autowired
    public BillService(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAll(){
        try{
            return billRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
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

    public void update(Bill bill) throws Exception {
        try {
            if (billRepository.existsById(bill.getId())) {
                billRepository.save(bill);
            } else {
                throw new Exception("Invalid Id");
            }
        }catch(Exception ex){
            throw ex;
        }
    }

}
