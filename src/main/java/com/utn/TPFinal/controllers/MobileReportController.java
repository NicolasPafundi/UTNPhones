package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.services.MobileReportService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/MobileReport")
public class MobileReportController {

    private final MobileReportService mobileReportService;
    private final SessionManager sessionManager;

    @Autowired
    public MobileReportController(MobileReportService mobileReportService, SessionManager sessionManager){
        this.mobileReportService= mobileReportService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/Client/callByDate")
    public ResponseEntity<List<MobileReportUserCalls>> getCallsByUserByDate(@RequestHeader("Authorization") String sessionToken, @RequestBody MobileReportFilter mobileReportFilter) throws ResourceNotExistException, Exception {
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            List<MobileReportUserCalls> mobileReportUserCalls = mobileReportService.getCallsByUserByDate(mobileReportFilter.getDateFrom(),mobileReportFilter.getDateTo(),userId);
            return (mobileReportUserCalls.size() > 0) ? ResponseEntity.ok(mobileReportUserCalls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Client/billsByDate")
    public ResponseEntity<List<MobileReportUserBills>> getBillsByUserByDate(@RequestHeader("Authorization") String sessionToken,@RequestBody MobileReportFilter mobileReportFilter) throws ResourceNotExistException, Exception {
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            List<MobileReportUserBills> mobileReportUserBills = mobileReportService.getBillsByUserByDate(mobileReportFilter.getDateFrom(),mobileReportFilter.getDateTo(),userId);
            return (mobileReportUserBills.size() > 0) ? ResponseEntity.ok(mobileReportUserBills) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Client/destinationRank/{top?}")
    public ResponseEntity<List<MobileReportUserCallsRank>> getDestinationRankByUser(@RequestHeader("Authorization") String sessionToken,@RequestParam(value= "top",required = false, defaultValue = "10") Integer top) throws ResourceNotExistException, Exception {
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            List<MobileReportUserCallsRank> mobileReportUserCallsRank = mobileReportService.getDestinationRankByUser(userId,top);
            return (mobileReportUserCallsRank.size() > 0) ? ResponseEntity.ok(mobileReportUserCallsRank) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

}
