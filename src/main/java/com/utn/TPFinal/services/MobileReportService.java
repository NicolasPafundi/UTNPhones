package com.utn.TPFinal.services;

import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.repositories.IMobileReportRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MobileReportService {

    private IMobileReportRepository mobileReportRepository;
    private IUserRepository userRepository;

    @Autowired
    public MobileReportService (IMobileReportRepository mobileReportRepository, IUserRepository userRepository){
        this.mobileReportRepository = mobileReportRepository;
        this.userRepository = userRepository;
    }

    public List<MobileReportUserCalls> getCallsByUserByDate(Date dateFrom, Date dateTo, Integer userId) throws UserNotexistException
    {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotexistException());
        return mobileReportRepository.getCallsByUserByDate(dateFrom, dateTo, userId);
    }

    public List<MobileReportUserBills> getBillsByUserByDate(Date dateFrom, Date dateTo, Integer userId) throws  UserNotexistException
    {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotexistException());
        return mobileReportRepository.getBillsByUserByDate(dateFrom, dateTo, userId);
    }

    public List<MobileReportUserCallsRank> getDestinationRankByUser(Integer userId, Integer top) throws UserNotexistException
    {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotexistException());
        return mobileReportRepository.getDestinationRankByUser(userId,top);

    }

}
