package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.MobileReportController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.dtos.CallsReportFilter;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import com.utn.TPFinal.model.projections.ReportCallsByUserByDate;
import com.utn.TPFinal.services.MobileReportService;
import com.utn.TPFinal.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class MobileReportControllerTest {

    MobileReportController controller;
    MobileReportService service;
    SessionManager sessionManagerService;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Before
    public void setUp() {
        service = mock(MobileReportService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new MobileReportController(service,sessionManagerService);
    }

    @Test
    public void getCallsByUserByDateOk() throws ResourceNotExistException, Exception {
        UserType userType = new UserType();
        userType.setName(UserTypes.EMPLOYEE);
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);

        List<MobileReportUserCalls> mobileReportUserCallsList= new ArrayList<>();

        MobileReportUserCalls mobileReportUserCalls = factory.createProjection(MobileReportUserCalls.class);
        mobileReportUserCalls.setDate(new Date());
        mobileReportUserCalls.setDestination("test");
        mobileReportUserCalls.setLine("line");
        mobileReportUserCalls.setMinDuration(0);
        mobileReportUserCalls.setTotalAmount(0);
        mobileReportUserCalls.setMinPrice(0);

        mobileReportUserCallsList.add(mobileReportUserCalls);

        MobileReportFilter mobileReportFilter = new MobileReportFilter();
        mobileReportFilter.setDateFrom(new Date());
        mobileReportFilter.setDateTo(new Date());

        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getCallsByUserByDate(mobileReportFilter.getDateFrom(),mobileReportFilter.getDateTo(),1)).thenReturn(mobileReportUserCallsList);
        ResponseEntity<List<MobileReportUserCalls>> returnedMobileReportUserCallsList= controller.getCallsByUserByDate("1",mobileReportFilter);

        assertEquals(returnedMobileReportUserCallsList.getBody().size(), 1);
        assertEquals(returnedMobileReportUserCallsList.getBody().get(0), mobileReportUserCallsList.get(0));

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getCallsByUserByDate(mobileReportFilter.getDateFrom(),mobileReportFilter.getDateTo(),1);
    }

    @Test
    public void getBillsByUserByDateOk() throws ResourceNotExistException, Exception {
        UserType userType = new UserType();
        userType.setName(UserTypes.EMPLOYEE);
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);

        MobileReportFilter mobileReportFilter = new MobileReportFilter();
        mobileReportFilter.setDateFrom(new Date());
        mobileReportFilter.setDateTo(new Date());

        List<MobileReportUserBills> mobileReportUserBillsList= new ArrayList<>();

        MobileReportUserBills mobileReportUserBill = factory.createProjection(MobileReportUserBills.class);
        mobileReportUserBill.setCallsAmount(1);
        mobileReportUserBill.setPayDay(new Date());
        mobileReportUserBill.setBillNumber(1);
        mobileReportUserBill.setCostPrice(1);
        mobileReportUserBill.setCreationDay(new Date());
        mobileReportUserBill.setLineNumber("1");
        mobileReportUserBill.setTotalPrice(1);

        mobileReportUserBillsList.add(mobileReportUserBill);


        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getBillsByUserByDate(mobileReportFilter.getDateFrom(),mobileReportFilter.getDateTo(),1)).thenReturn(mobileReportUserBillsList);
        ResponseEntity<List<MobileReportUserBills>> returnedMobileReportUserBills= controller.getBillsByUserByDate("1", mobileReportFilter);

        assertNotNull(returnedMobileReportUserBills);
        assertEquals(returnedMobileReportUserBills.getBody().size(), 1);
        assertEquals(returnedMobileReportUserBills.getBody().get(0), mobileReportUserBillsList.get(0));

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getBillsByUserByDate(mobileReportFilter.getDateFrom(),mobileReportFilter.getDateTo(), 1);
    }

    @Test
    public void getDestinationRankByUserOk() throws ResourceNotExistException, Exception {
        UserType userType = new UserType();
        userType.setName(UserTypes.EMPLOYEE);
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);

        List<MobileReportUserCallsRank> mobileReportUserCallsRanks= new ArrayList<>();

        MobileReportUserCallsRank mobileReportUserCallsRank = factory.createProjection(MobileReportUserCallsRank.class);
        mobileReportUserCallsRank.setDestination("1");
        mobileReportUserCallsRank.setCallAmount(1);

        mobileReportUserCallsRanks.add(mobileReportUserCallsRank);


        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getDestinationRankByUser(1,10)).thenReturn(mobileReportUserCallsRanks);
        ResponseEntity<List<MobileReportUserCallsRank>> returnedMobileReportUserCallsRank= controller.getDestinationRankByUser("1", 10);

        assertNotNull(returnedMobileReportUserCallsRank);
        assertEquals(returnedMobileReportUserCallsRank.getBody().size(), 1);
        assertEquals(returnedMobileReportUserCallsRank.getBody().get(0), mobileReportUserCallsRanks.get(0));

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getDestinationRankByUser(1,10);
    }
}