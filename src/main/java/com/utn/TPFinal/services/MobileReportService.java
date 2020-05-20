package com.utn.TPFinal.services;

import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.repositories.IMobileReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileReportService {

    private IMobileReportRepository mobileReportRepository;

    @Autowired
    public MobileReportService (IMobileReportRepository mobileReportRepository){
        this.mobileReportRepository = mobileReportRepository;
    }

    public List<MobileReportUserCalls> getCallsByUserByDate(MobileReportFilter mobileReportFilter)
    {
        try{
            return mobileReportRepository.getCallsByUserByDate(mobileReportFilter.getDateFrom(), mobileReportFilter.getDateTo(), mobileReportFilter.getUserId());
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<MobileReportUserBills> getBillsByUserByDate(MobileReportFilter mobileReportFilter)
    {
        try{
            return mobileReportRepository.getBillsByUserByDate(mobileReportFilter.getDateFrom(), mobileReportFilter.getDateTo(), mobileReportFilter.getUserId());
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<MobileReportUserCallsRank> getDestinationRankByUser(MobileReportFilter mobileReportFilter)
    {
        try{
            return mobileReportRepository.getDestinationRankByUser(mobileReportFilter.getUserId());
        }catch(Exception ex){
            throw ex;
        }
    }

}
