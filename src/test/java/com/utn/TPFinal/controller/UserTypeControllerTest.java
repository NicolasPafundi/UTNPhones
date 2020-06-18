package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.UserTypeController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.services.UserTypeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserTypeControllerTest {

    UserTypeController controller;
    UserTypeService service;

    @Before
    public void setUp() {
        service = mock(UserTypeService.class);
        controller = new UserTypeController(service);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, Exception {
        UserType UserType = new UserType();
        UserType.setId(1);
        List<UserType> UserTypes= new ArrayList<>();
        UserTypes.add(UserType);

        when(service.getAll()).thenReturn(UserTypes);
        ResponseEntity<List<UserType>> returnedUserTypes= controller.getAll("1");

        assertEquals(returnedUserTypes.getBody().size(), 1);
        assertEquals(returnedUserTypes.getBody().get(0), UserTypes.get(0));

        verify(service, times(1)).getAll();
    }
}
