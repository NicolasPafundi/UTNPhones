package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.PhoneLineController;
import com.utn.TPFinal.exceptions.UserNotexistException;
import com.utn.TPFinal.services.PhoneLineService;
import com.utn.TPFinal.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PhoneLineControllerTest {

    PhoneLineController controller;
    PhoneLineService service;
    SessionManager sessionManagerService;

    @Before
    public void setUp() {
        service = mock(PhoneLineService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new PhoneLineController(service,sessionManagerService);
    }

    @Test
    public void TestGetCantCellphoneLinesOK() throws UserNotexistException {

        when(service.getCantCellphoneLines()).thenReturn(2);
        ResponseEntity<Integer> cellphonelines= controller.getCantCellphoneLines("1");

        assertNotNull(cellphonelines.getBody());
        assertTrue(cellphonelines.getBody() == 2);

        verify(service, times(1)).getCantCellphoneLines();
    }
}