package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.PhoneLineController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.entities.PhoneLine;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.services.PhoneLineService;
import com.utn.TPFinal.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PhoneLineControllerTest {

    PhoneLineController controller;
    PhoneLineService service;
    SessionManager sessionManagerService;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Before
    public void setUp() {
        service = mock(PhoneLineService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new PhoneLineController(service,sessionManagerService);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, Exception {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        List<PhoneLine> PhoneLines= new ArrayList<>();
        PhoneLines.add(PhoneLine);

        when(service.getAll()).thenReturn(PhoneLines);
        ResponseEntity<List<PhoneLine>> returnedPhoneLines= controller.getAll("1");

        assertEquals(returnedPhoneLines.getBody().size(), 1);
        assertEquals(returnedPhoneLines.getBody().get(0), PhoneLines.get(0));

        verify(service, times(1)).getAll();
    }

    @Test
    public void getByIdOk() throws ResourceNotExistException, Exception {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);

        when(service.getById(1)).thenReturn(PhoneLine);
        ResponseEntity<PhoneLine> returnedPhoneLine= controller.getById("1",1);

        assertNotNull(returnedPhoneLine);
        assertEquals(returnedPhoneLine.getBody(), PhoneLine);

        verify(service, times(1)).getById(1);
    }

    @Test
    public void GetByUserIdOk() throws ResourceNotExistException, Exception {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        List<PhoneLine> PhoneLines= new ArrayList<>();
        PhoneLines.add(PhoneLine);

        when(service.getByUser(1)).thenReturn(PhoneLines);
        ResponseEntity<List<PhoneLine>> returnedPhoneLines= controller.getByUserId("1",1);

        assertNotNull(returnedPhoneLines);
        assertEquals(returnedPhoneLines.getBody().size(), 1);
        assertEquals(returnedPhoneLines.getBody().get(0), PhoneLines.get(0));

        verify(service, times(1)).getByUser(1);
    }


    @Test
    public void getByCurrentUserOk() throws ResourceNotExistException, Exception {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        List<PhoneLine> PhoneLines= new ArrayList<>();
        PhoneLines.add(PhoneLine);

        UserType userType = new UserType();
        userType.setName(UserTypes.EMPLOYEE);
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setUserType(userType);

        when(sessionManagerService.getCurrentUser("1")).thenReturn(user);
        when(service.getByUser(1)).thenReturn(PhoneLines);
        ResponseEntity<List<PhoneLine>> returnedPhoneLines= controller.getByCurrentUser("1");

        assertNotNull(returnedPhoneLines);
        assertEquals(returnedPhoneLines.getBody().size(), 1);
        assertEquals(returnedPhoneLines.getBody().get(0), PhoneLines.get(0));

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getByUser(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, Exception, ValidationException {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);

        doNothing().when(service).update(PhoneLine);
        ResponseEntity returned= controller.update("1",PhoneLine);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(service, times(1)).update(PhoneLine);
    }

    @Test
    public void removeOk() throws ResourceNotExistException, Exception {

        doNothing().when(service).remove(1);
        ResponseEntity returned= controller.remove("1",1);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(service, times(1)).remove(1);
    }
}
