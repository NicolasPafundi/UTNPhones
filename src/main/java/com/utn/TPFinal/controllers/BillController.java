package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.entities.Bill;
import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Bills")
public class BillController {

    @Autowired
    private final BillService billService;

    public BillController (BillService billService){
        this.billService = billService;
    }

    @GetMapping("/User/{id}")
    public List<Bill> getByUserID(@PathVariable Integer id){
        try{
            return billService.getByUserID(id);
        }catch (Exception ex){
            throw ex;
        }
    }


}
