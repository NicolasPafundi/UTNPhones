package com.utn.TPFinal.Service;

import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
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
    public void TestGetBillsByUserByDateOk() throws UserNotexistException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        List<MobileReportUserCalls> mobileReportUserCallsList= new ArrayList<>();

        MobileReportUserCalls mobileReportUserCalls = factory.createProjection(MobileReportUserCalls.class);
        mobileReportUserCalls.setDate(new Date(0,0,0));
        mobileReportUserCalls.setDestination("test");
        mobileReportUserCalls.setLine("line");
        mobileReportUserCalls.setMinDuration(0);
        mobileReportUserCalls.setTotalAmount(0);
        mobileReportUserCalls.setMinPrice(0);

        mobileReportUserCallsList.add(mobileReportUserCalls);

        MobileReportFilter mobileReportFilter = new MobileReportFilter();
        mobileReportFilter.setDateFrom(new Date(0,0,0));
        mobileReportFilter.setDateTo(new Date(0,0,0));
        mobileReportFilter.setUserId(1);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(mobileReportRepository.getCallsByUserByDate(new Date(0,0,0),new Date(0,0,0),1)).thenReturn(mobileReportUserCallsList);
        List<MobileReportUserCalls> returnedMobileReportUserCallsList= service.getCallsByUserByDate(mobileReportFilter);

        assertEquals(returnedMobileReportUserCallsList.size(), 1);
        assertEquals(returnedMobileReportUserCallsList.get(0), mobileReportUserCallsList.get(0));

        verify(userRepository, times(1)).findById(1);
        verify(mobileReportRepository, times(1)).getCallsByUserByDate(new Date(0,0,0),new Date(0,0,0),1);
    }

    /*
    @Test(expected = UserNotexistException.class)
    public void TestGetBillsByUserByDateUserNotExist() throws UserNotexistException {
        MobileReportFilter mobileReportFilter = new MobileReportFilter();
        mobileReportFilter.setDateFrom(new Date(0,0,0));
        mobileReportFilter.setDateTo(new Date(0,0,0));
        mobileReportFilter.setUserId(1);

        when(userRepository.findById(1)).thenReturn(null);
        service.getCallsByUserByDate(mobileReportFilter);
    }*/



}