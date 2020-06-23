package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.PhoneLineTypeController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.services.PhoneLineTypeService;
import com.utn.TPFinal.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PhoneLineTypeControllerTest {

    PhoneLineTypeController controller;
    PhoneLineTypeService service;

    @Before
    public void setUp() {
        service = mock(PhoneLineTypeService.class);
        controller = new PhoneLineTypeController(service);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, Exception {
        PhoneLineType PhoneLineType = new PhoneLineType();
        PhoneLineType.setId(1);
        List<PhoneLineType> PhoneLineTypes= new ArrayList<>();
        PhoneLineTypes.add(PhoneLineType);

        when(service.getAll()).thenReturn(PhoneLineTypes);
        ResponseEntity<List<PhoneLineType>> returnedPhoneLineTypes= controller.getAll("1");

        assertEquals(returnedPhoneLineTypes.getBody().size(), 1);
        assertEquals(returnedPhoneLineTypes.getBody().get(0), PhoneLineTypes.get(0));

        verify(service, times(1)).getAll();
    }

    @Test(expected = Exception.class)
    public void getAllException() throws ResourceNotExistException, Exception {
        PhoneLineType PhoneLineType = new PhoneLineType();
        PhoneLineType.setId(1);
        List<PhoneLineType> PhoneLineTypes= new ArrayList<>();
        PhoneLineTypes.add(PhoneLineType);

        when(service.getAll()).thenThrow((Class<? extends Throwable>) null);
        controller.getAll("1");
    }
}
