package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.RateController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.RatesReport;
import com.utn.TPFinal.services.RateService;
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

public class RateControllerTest {

    RateController controller;
    RateService service;
    SessionManager sessionManagerService;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Before
    public void setUp() {
        service = mock(RateService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new RateController(service,sessionManagerService);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, Exception {
        Rate Rate = new Rate();
        Rate.setId(1);
        List<Rate> Rates= new ArrayList<>();
        Rates.add(Rate);

        when(service.getAll()).thenReturn(Rates);
        ResponseEntity<List<Rate>> returnedRates= controller.getAll("1");

        assertEquals(returnedRates.getBody().size(), 1);
        assertEquals(returnedRates.getBody().get(0), Rates.get(0));

        verify(service, times(1)).getAll();
    }

    @Test
    public void getByIdOk() throws ResourceNotExistException, Exception {
        Rate Rate = new Rate();
        Rate.setId(1);

        when(service.getById(1)).thenReturn(Rate);
        ResponseEntity<Rate> returnedRate= controller.getById("1",1);

        assertNotNull(returnedRate);
        assertEquals(returnedRate.getBody(), Rate);

        verify(service, times(1)).getById(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, Exception, ValidationException {
        Rate Rate = new Rate();
        Rate.setId(1);

        doNothing().when(service).update(Rate);
        ResponseEntity returned= controller.update("1",Rate);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(service, times(1)).update(Rate);
    }

    @Test
    public void removeOk() throws ResourceNotExistException, Exception {

        doNothing().when(service).remove(1);
        ResponseEntity returned= controller.remove("1",1);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(service, times(1)).remove(1);
    }

    @Test
    public void getRatesBetweenAreaCodesOk() throws ResourceNotExistException, Exception {
        Integer areaCodeFrom =1;
        Integer areaCodeTo = 1;

        List<RatesReport> ratesreports=new ArrayList<>();

        RatesReport ratesReport = factory.createProjection(RatesReport.class);
        ratesReport.setDestino("1");
        ratesReport.setOrigen("1");
        ratesReport.setPrecio(1);
        ratesreports.add(ratesReport);


        when(service.getRatesBetweenAreaCodes(1,1)).thenReturn(ratesreports);
        ResponseEntity<List<RatesReport>> returnedRate= controller.getRatesBetweenAreaCodes("1",1,1);

        assertNotNull(returnedRate.getBody());
        assertEquals(returnedRate.getBody().get(0), ratesreports.get(0));

        verify(service, times(1)).getRatesBetweenAreaCodes(1,1);
    }
}
