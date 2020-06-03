package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.CallController;
import com.utn.TPFinal.controllers.MobileReportController;
import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.model.entities.*;
import com.utn.TPFinal.services.CallService;
import com.utn.TPFinal.services.MobileReportService;
import com.utn.TPFinal.session.SessionManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CallControllerTest {

    CallController controller;
    CallService service;
    SessionManager sessionManagerService;

    @Before
    public void setUp() {
        service = mock(CallService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new CallController(service,sessionManagerService);
    }

    @Test
    public void getLastCallByDniOk() throws UserNotexistException {
        UserType userType = new UserType();
        userType.setName("CLIENTE");
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);
        Rate rate = new Rate();


        Call call = new Call();


        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getLastCallByDni("123")).thenReturn(call);

        assertEquals(java.util.Optional.ofNullable(call.getId()), 1);

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getLastCallByDni("123");
    }


    @Test
    public void getLastCallByDniNull() throws UserNotexistException {
        UserType userType = new UserType();
        userType.setName("CLIENTE");
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);
        Rate rate = new Rate();


        Call call = new Call();


        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getLastCallByDni("123")).thenReturn(null);

        assertEquals(java.util.Optional.ofNullable(call.getId()), null);

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getLastCallByDni("123");
    }


}
