package com.utn.TPFinal.controllers;

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

    @PostMapping("/getCallsByUserByDate")
    public ResponseEntity<List<MobileReportUserCalls>> getCallsByUserByDate(@RequestHeader("Authorization") String sessionToken, @RequestBody MobileReportFilter mobileReportFilter){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && (user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId() == mobileReportFilter.getUserId())){
                List<MobileReportUserCalls> mobileReportUserCalls = mobileReportService.getCallsByUserByDate(mobileReportFilter);
                return (mobileReportUserCalls.size() > 0) ? ResponseEntity.ok(mobileReportUserCalls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/getBillsByUserByDate")
    public ResponseEntity<List<MobileReportUserBills>> getBillsByUserByDate(@RequestHeader("Authorization") String sessionToken,@RequestBody MobileReportFilter mobileReportFilter){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && (user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId() == mobileReportFilter.getUserId())){
                List<MobileReportUserBills> mobileReportUserBills = mobileReportService.getBillsByUserByDate(mobileReportFilter);
                return (mobileReportUserBills.size() > 0) ? ResponseEntity.ok(mobileReportUserBills) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/getDestinationRankByUser")
    public ResponseEntity<List<MobileReportUserCallsRank>> getDestinationRankByUser(@RequestHeader("Authorization") String sessionToken,@RequestBody MobileReportFilter mobileReportFilter){
        try{
            User user = sessionManager.getCurrentUser(sessionToken);

            if(user!=null && (user.getUserType().getName().toUpperCase().equals(UserTypeEnum.EMPLEADO.name()) || user.getId() == mobileReportFilter.getUserId())){
                List<MobileReportUserCallsRank> mobileReportUserCallsRank = mobileReportService.getDestinationRankByUser(mobileReportFilter);
                return (mobileReportUserCallsRank.size() > 0) ? ResponseEntity.ok(mobileReportUserCallsRank) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception ex){
            throw ex;
        }
    }


}
