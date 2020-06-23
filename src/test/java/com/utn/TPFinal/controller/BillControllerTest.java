package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.BillController;
import com.utn.TPFinal.controllers.MobileReportController;
import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.Bill;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.services.BillService;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BillControllerTest {

    BillController controller;
    BillService service;
    SessionManager sessionManagerService;

    @Before
    public void setUp() {
        service = mock(BillService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new BillController(service,sessionManagerService);
    }

    @Test
    public void getByUserIDOk() throws ResourceNotExistException, Exception {
        Bill bill = new Bill();
        bill.setId(1);
        List<Bill> bills= new ArrayList<>();
        bills.add(bill);

        when(service.getByUserID(1)).thenReturn(bills);
        ResponseEntity<List<Bill>> returnedbills= controller.getByUserID("1",1);

        assertEquals(returnedbills.getBody().size(), 1);
        assertEquals(returnedbills.getBody().get(0), bills.get(0));

        verify(service, times(1)).getByUserID(1);
    }

    @Test(expected = ResourceNotExistException.class)
    public void getByUserIDThrowException() throws ResourceNotExistException, Exception {
        when(service.getByUserID(any())).thenThrow(new ResourceNotExistException("test"));
        controller.getByUserID("1",1);
    }

    @Test
    public void getByCurrentUserIDOk() throws ResourceNotExistException, Exception {
        Bill bill = new Bill();
        bill.setId(1);
        List<Bill> bills= new ArrayList<>();
        bills.add(bill);
        UserType userType = new UserType();
        userType.setName(UserTypes.EMPLOYEE);
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");


        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getByUserID(1)).thenReturn(bills);
        ResponseEntity<List<Bill>> returnedbills= controller.getByCurrentUser("1");

        assertEquals(returnedbills.getBody().size(), 1);
        assertEquals(returnedbills.getBody().get(0), bills.get(0));

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getByUserID(1);
    }

    @Test(expected = ResourceNotExistException.class)
    public void getByCurrentUserIDException() throws ResourceNotExistException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getByUserID(1)).thenThrow(new ResourceNotExistException("test"));
        controller.getByCurrentUser("1");
    }
}
