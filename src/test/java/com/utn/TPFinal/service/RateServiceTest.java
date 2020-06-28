package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.dtos.CallsReportFilter;
import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.RatesReport;
import com.utn.TPFinal.model.projections.ReportCallsByUserByDate;
import com.utn.TPFinal.repositories.IRateRepository;
import com.utn.TPFinal.repositories.ICityRepository;
import com.utn.TPFinal.services.RateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class RateServiceTest {

    RateService service;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Mock
    IRateRepository RateRepository;
    @Mock
    ICityRepository CityRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new RateService(RateRepository,CityRepository);
    }

    @Test
    public void GetAllOk() throws ResourceNotExistException, ResourceNotExistException {
        Rate Rate = new Rate();
        Rate.setId(1);
        Rate.setPrice(1);

        List<Rate> Rates = new ArrayList<>();
        Rates.add(Rate);

        when(RateRepository.findAll()).thenReturn(Rates);
        List<Rate> returnedRates= service.getAll();

        assertEquals(returnedRates.size(), 1);
        assertEquals(returnedRates.get(0), Rates.get(0));

        verify(RateRepository, times(1)).findAll();
    }


    @Test
    public void getByIdOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        Rate Rate = new Rate();
        Rate.setId(1);
        Rate.setPrice(1);

        when(RateRepository.findById(1)).thenReturn(java.util.Optional.of(Rate));
        Rate returnedRate= service.getById(1);

        assertNotNull(returnedRate);
        assertEquals(returnedRate, Rate);

        verify(RateRepository, times(1)).findById(1);
    }

    @Test
    public void addOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption, ValidationException {
        Rate Rate = new Rate();
        Rate.setId(1);
        Rate.setPrice(1);

        when(RateRepository.existsById(1)).thenReturn(false);
        when(RateRepository.save(Rate)).thenReturn(Rate);
        int returnedId= service.add(Rate);

        assertNotNull(returnedId);
        assertEquals(1, returnedId);

        verify(RateRepository, times(1)).existsById(1);
        verify(RateRepository, times(1)).save(Rate);
    }

    @Test
    public void removeOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        Rate Rate = new Rate();
        Rate.setId(1);
        Rate.setPrice(1);

        when(RateRepository.findById(1)).thenReturn(java.util.Optional.of(Rate));
        doNothing().when(RateRepository).deleteById(1);
        service.remove(1);

        verify(RateRepository, times(1)).findById(1);
        verify(RateRepository, times(1)).deleteById(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption, ValidationException {
        Rate Rate = new Rate();
        Rate.setId(1);
        Rate.setPrice(1);

        when(RateRepository.findById(1)).thenReturn(java.util.Optional.of(Rate));
        when(RateRepository.save(Rate)).thenReturn(Rate);
        service.update(Rate);

        verify(RateRepository, times(1)).findById(1);
        verify(RateRepository, times(1)).save(Rate);
    }

    @Test
    public void getReportCallsByUserByDateOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        CallsReportFilter callsReportFilter = new CallsReportFilter();
        callsReportFilter.setUserId(1);
        callsReportFilter.setDateTo(new Date());
        callsReportFilter.setDateFrom(new Date());

        List<RatesReport> ratesReportsList = new ArrayList<>();

        RatesReport ratesReport = factory.createProjection(RatesReport.class);
        ratesReport.setPrecio(1);
        ratesReport.setOrigen("1");
        ratesReport.setDestino("1");

        ratesReportsList.add(ratesReport);

        City city = new City();
        city.setName("test");
        city.setAreaCode(1);
        city.setId(1);

        when(CityRepository.findByAreaCode(1)).thenReturn(city);
        when(RateRepository.getRatesBetweenAreaCodes(1,1)).thenReturn(ratesReportsList);
        List<RatesReport> responseRatesReportsList= service.getRatesBetweenAreaCodes(1,1);

        assertNotNull(ratesReportsList);
        assertEquals(ratesReportsList.size(), responseRatesReportsList.size());
        assertEquals(ratesReportsList.get(0), responseRatesReportsList.get(0));

        verify(CityRepository, times(2)).findByAreaCode(1);
        verify(RateRepository, times(1)).getRatesBetweenAreaCodes(1,1);
    }

    @Test(expected = Exception.class)
    public void GetAllException() throws ResourceNotExistException, ResourceNotExistException {
        when(RateRepository.findAll()).thenThrow((Class<? extends Throwable>) null);
        List<Rate> returnedRates= service.getAll();
    }


    @Test(expected = ResourceNotExistException.class)
    public void getByIdException() throws ResourceNotExistException, ResourceNotExistException, Exception {
        when(RateRepository.findById(1)).thenReturn(Optional.empty());
        Rate returnedRate= service.getById(1);
    }

    @Test(expected = ResourceAlreadyExistExeption.class)
    public void addException() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption, ValidationException {
        Rate Rate = new Rate();
        Rate.setId(1);
        Rate.setPrice(1);

        when(RateRepository.existsById(1)).thenReturn(true);
        int returnedId= service.add(Rate);
    }

    @Test(expected = ResourceNotExistException.class)
    public void removeException() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        when(RateRepository.findById(1)).thenReturn(Optional.empty());
        service.remove(1);
    }

    @Test(expected = ResourceNotExistException.class)
    public void updateException() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption, ValidationException {
        Rate Rate = new Rate();
        Rate.setId(1);
        Rate.setPrice(1);

        when(RateRepository.findById(1)).thenReturn(Optional.empty());
        service.update(Rate);
    }

    @Test(expected = ResourceNotExistException.class)
    public void getReportCallsByUserByDateException() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        when(CityRepository.findByAreaCode(1)).thenReturn(null);
        List<RatesReport> responseRatesReportsList= service.getRatesBetweenAreaCodes(1,1);
    }
}
