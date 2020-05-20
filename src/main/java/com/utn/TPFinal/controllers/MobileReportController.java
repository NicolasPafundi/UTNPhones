package com.utn.TPFinal.controllers;

import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.services.MobileReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/MobileReport")
public class MobileReportController {

    private final MobileReportService mobileReportService;

    @Autowired
    public MobileReportController(MobileReportService mobileReportService){
        this.mobileReportService= mobileReportService;
    }

    @PostMapping("/getCallsByUserByDate")
    public List<MobileReportUserCalls> getCallsByUserByDate(@RequestBody MobileReportFilter mobileReportFilter){
        try{
            return mobileReportService.getCallsByUserByDate(mobileReportFilter);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/getBillsByUserByDate")
    public List<MobileReportUserBills> getBillsByUserByDate(@RequestBody MobileReportFilter mobileReportFilter){
        try{
            return mobileReportService.getBillsByUserByDate(mobileReportFilter);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/getDestinationRankByUser")
    public List<MobileReportUserCallsRank> getDestinationRankByUser(@RequestBody MobileReportFilter mobileReportFilter){
        try{
            return mobileReportService.getDestinationRankByUser(mobileReportFilter);
        }catch (Exception ex){
            throw ex;
        }
    }


}
