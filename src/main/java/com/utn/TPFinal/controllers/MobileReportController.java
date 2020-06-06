package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.services.MobileReportService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @PostMapping("/Employee/callsByUserByDate")
    public ResponseEntity<List<MobileReportUserCalls>> getCallsByUserByDate(@RequestHeader("Authorization") String sessionToken, @RequestBody MobileReportFilter mobileReportFilter) throws UserNotexistException{
        try{
            List<MobileReportUserCalls> mobileReportUserCalls = mobileReportService.getCallsByUserByDate(mobileReportFilter);
            return (mobileReportUserCalls.size() > 0) ? ResponseEntity.ok(mobileReportUserCalls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/billsByUserByDate")
    public ResponseEntity<List<MobileReportUserBills>> getBillsByUserByDate(@RequestHeader("Authorization") String sessionToken,@RequestBody MobileReportFilter mobileReportFilter) throws UserNotexistException{
        try{
            List<MobileReportUserBills> mobileReportUserBills = mobileReportService.getBillsByUserByDate(mobileReportFilter);
            return (mobileReportUserBills.size() > 0) ? ResponseEntity.ok(mobileReportUserBills) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Client/billsByCurrentUserByDate")
    public ResponseEntity<List<MobileReportUserBills>> getBillsByCurrentUserByDate(@RequestHeader("Authorization") String sessionToken,@PathVariable Date dateFrom,@PathVariable Date dateTo ) throws UserNotexistException{
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            MobileReportFilter mobileReportFilter = new MobileReportFilter(userId,dateFrom,dateTo);
            List<MobileReportUserBills> mobileReportUserBills = mobileReportService.getBillsByUserByDate(mobileReportFilter);
            return (mobileReportUserBills.size() > 0) ? ResponseEntity.ok(mobileReportUserBills) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/destinationRankByUser/{userId}")
    public ResponseEntity<List<MobileReportUserCallsRank>> getDestinationRankByUser(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer userId) throws UserNotexistException {
        try{
            List<MobileReportUserCallsRank> mobileReportUserCallsRank = mobileReportService.getDestinationRankByUser(userId);
            return (mobileReportUserCallsRank.size() > 0) ? ResponseEntity.ok(mobileReportUserCallsRank) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }


}
