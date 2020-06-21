package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.City;
import com.utn.TPFinal.model.entities.State;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.repositories.ICityRepository;
import com.utn.TPFinal.repositories.IStateRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import com.utn.TPFinal.services.CityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class CityServiceTest {

    CityService service;

    @Mock
    ICityRepository CityRepository;
    @Mock
    IStateRepository stateRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new CityService(CityRepository,stateRepository);
    }

    @Test
    public void getAllByStateOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        City City = new City();
        City.setId(1);
        City.setAreaCode(1);
        City.setName("test");

        List<City> Citys = new ArrayList<>();
        Citys.add(City);

        State state = new State();
        state.setId(1);
        state.setName("1");

        when(stateRepository.findById(1)).thenReturn(java.util.Optional.of(state));
        when(CityRepository.getAllByState(1)).thenReturn(Citys);
        List<City> returnedCitys= service.getAllByState(1);

        assertEquals(returnedCitys.size(), 1);
        assertEquals(returnedCitys.get(0), Citys.get(0));

        verify(CityRepository, times(1)).getAllByState(1);
        verify(stateRepository, times(1)).findById(1);
    }
}
