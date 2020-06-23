package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.CityController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.services.CityService;
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

public class CityControllerTest {
    
    CityController controller;
    CityService service;
    SessionManager sessionManagerService;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Before
    public void setUp() {
        service = mock(CityService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new CityController(service,sessionManagerService);
    }

    @Test
    public void getAllByStateOk() throws ResourceNotExistException, Exception {
        City City = new City();
        City.setId(1);
        List<City> Cities= new ArrayList<>();
        Cities.add(City);

        when(service.getAllByState(1)).thenReturn(Cities);
        ResponseEntity<List<City>> returnedCities= controller.getAllByState("1",1);

        assertNotNull(returnedCities);
        assertEquals(returnedCities.getBody().size(), 1);
        assertEquals(returnedCities.getBody().get(0), Cities.get(0));

        verify(service, times(1)).getAllByState(1);
    }

    @Test(expected = ResourceNotExistException.class)
    public void getAllByStateException() throws ResourceNotExistException, Exception {
        when(service.getAllByState(1)).thenThrow(new ResourceNotExistException("test"));
        ResponseEntity<List<City>> returnedCities= controller.getAllByState("1",1);
    }
}
