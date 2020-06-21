package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.repositories.IMobileReportRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import com.utn.TPFinal.services.MobileReportService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MobileReportServiceTest {

    MobileReportService service;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Mock
    IMobileReportRepository mobileReportRepository;
    @Mock
    IUserRepository userRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new MobileReportService(mobileReportRepository,userRepository);
    }

    @Test
    public void TestGetCallsByUserByDateOk() throws ResourceNotExistException, ResourceNotExistException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        
        Date date = new Date();

        List<MobileReportUserCalls> mobileReportUserCallsList= new ArrayList<>();

        MobileReportUserCalls mobileReportUserCalls = factory.createProjection(MobileReportUserCalls.class);
        mobileReportUserCalls.setDate(date);
        mobileReportUserCalls.setDestination("test");
        mobileReportUserCalls.setLine("line");
        mobileReportUserCalls.setMinDuration(0);
        mobileReportUserCalls.setTotalAmount(0);
        mobileReportUserCalls.setMinPrice(0);

        mobileReportUserCallsList.add(mobileReportUserCalls);

        MobileReportFilter mobileReportFilter = new MobileReportFilter();
        mobileReportFilter.setDateFrom(date);
        mobileReportFilter.setDateTo(date);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(mobileReportRepository.getCallsByUserByDate(date,date,1)).thenReturn(mobileReportUserCallsList);
        List<MobileReportUserCalls> returnedMobileReportUserCallsList= service.getCallsByUserByDate(mobileReportFilter.getDateFrom(),mobileReportFilter.getDateTo(),1);

        assertEquals(1, returnedMobileReportUserCallsList.size());

        verify(userRepository, times(1)).findById(1);
        verify(mobileReportRepository, times(1)).getCallsByUserByDate(date,date,1);
    }

    @Test
    public void getBillsByUserByDateOk() throws ResourceNotExistException, ResourceNotExistException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        Date date = new Date();

        List<MobileReportUserBills> mobileReportUserBillsList= new ArrayList<>();

        MobileReportUserBills mobileReportUserBills = factory.createProjection(MobileReportUserBills.class);
        mobileReportUserBills.setTotalPrice(1);
        mobileReportUserBills.setLineNumber("1");
        mobileReportUserBills.setCreationDay(date);
        mobileReportUserBills.setCostPrice(1);
        mobileReportUserBills.setBillNumber(1);
        mobileReportUserBills.setPayDay(date);
        mobileReportUserBills.setCallsAmount(1);

        mobileReportUserBillsList.add(mobileReportUserBills);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(mobileReportRepository.getBillsByUserByDate(date,date,1)).thenReturn(mobileReportUserBillsList);
        List<MobileReportUserBills> returnedMobileReportUserBillsList= service.getBillsByUserByDate(date,date,1);

        assertEquals(1, returnedMobileReportUserBillsList.size());

        verify(userRepository, times(1)).findById(1);
        verify(mobileReportRepository, times(1)).getBillsByUserByDate(date,date,1);
    }

    @Test
    public void getDestinationRankByUserOk() throws ResourceNotExistException, ResourceNotExistException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        List<MobileReportUserCallsRank> mobileReportUserCallsRankList= new ArrayList<>();

        MobileReportUserCallsRank mobileReportUserCallsRank = factory.createProjection(MobileReportUserCallsRank.class);
        mobileReportUserCallsRank.setCallAmount(1);
        mobileReportUserCallsRank.setDestination("1");

        mobileReportUserCallsRankList.add(mobileReportUserCallsRank);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(mobileReportRepository.getDestinationRankByUser(1,10)).thenReturn(mobileReportUserCallsRankList);
        List<MobileReportUserCallsRank> returnedMobileReportUserCallsRanks= service.getDestinationRankByUser(1,10);

        assertEquals(returnedMobileReportUserCallsRanks.size(), 1);
        assertEquals(returnedMobileReportUserCallsRanks.get(0), mobileReportUserCallsRankList.get(0));

        verify(userRepository, times(1)).findById(1);
        verify(mobileReportRepository, times(1)).getDestinationRankByUser(1,10);
    }
}