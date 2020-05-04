package com.utn.TPFinal.Services;

import com.utn.TPFinal.Domain.Entities.Bill;
import com.utn.TPFinal.Repositories.Contracts.IBillRepository;
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

    public List<Bill> GetAll(){
        try{
            return billRepository.findAll();
        }catch(Exception ex){
            throw ex;
        }
    }

    public Bill GetById(Integer Id){
        try{
            return billRepository.findById(Id).get();
        }catch(Exception ex){
            throw ex;
        }
    }

    public int Add(Bill bill){
        try{
            return billRepository.save(bill).getId();
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Remove(Integer Id){
        try{
            billRepository.deleteById(Id);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void Update(Bill bill) throws Exception {
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
