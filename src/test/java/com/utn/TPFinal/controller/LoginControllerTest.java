package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.LoginController;
import com.utn.TPFinal.controllers.MobileReportController;
import com.utn.TPFinal.exceptions.InvalidLoginException;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.dtos.LoginInput;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.services.MobileReportService;
import com.utn.TPFinal.services.UserService;
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
import static org.mockito.Mockito.times;

public class LoginControllerTest {

    LoginController controller;
    UserService service;
    SessionManager sessionManagerService;

    @Before
    public void setUp() {
        service = mock(UserService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new LoginController(service,sessionManagerService);
    }

    @Test
    public void loginOk() throws ResourceNotExistException, Exception, ValidationException, InvalidLoginException {
        UserType userType = new UserType();
        userType.setName(UserTypes.EMPLOYEE);
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);

        LoginInput loginInput = new LoginInput();
        loginInput.setPassword("test");
        loginInput.setUserName("test");

        when(sessionManagerService.createSession(user)).thenReturn("token1234");
        when(service.getByUserNameAndPassword(loginInput)).thenReturn(user);
        ResponseEntity returnedToken= controller.login(loginInput);

        assertNotNull(returnedToken);

        verify(sessionManagerService, times(1)).createSession(user);
        verify(service, times(1)).getByUserNameAndPassword(loginInput);
    }

    @Test(expected = InvalidLoginException.class)
    public void loginException() throws ResourceNotExistException, Exception, ValidationException, InvalidLoginException {
        UserType userType = new UserType();
        userType.setName(UserTypes.EMPLOYEE);
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);

        LoginInput loginInput = new LoginInput();
        loginInput.setPassword("test");
        loginInput.setUserName("test");

        when(sessionManagerService.createSession(user)).thenReturn("token1234");
        when(service.getByUserNameAndPassword(loginInput)).thenThrow(new InvalidLoginException());
        ResponseEntity returnedToken= controller.login(loginInput);
    }

    @Test
    public void logoutOk() throws ResourceNotExistException, Exception, ValidationException, InvalidLoginException {

        String token="token1234";

        doNothing().when(sessionManagerService).removeSession(token);
        ResponseEntity returned= controller.logout(token);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(sessionManagerService, times(1)).removeSession(token);
    }
}
