package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.State;
import com.utn.TPFinal.repositories.IStateRepository;
import com.utn.TPFinal.services.StateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class StateServiceTest {

    StateService service;

    @Mock
    IStateRepository StateRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new StateService(StateRepository);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        State State = new State();
        State.setId(1);
        State.setName("test");

        List<State> States = new ArrayList<>();
        States.add(State);

        when(StateRepository.findAll()).thenReturn(States);
        List<State> returnedStates= service.getAll();

        assertEquals(returnedStates.size(), 1);
        assertEquals(returnedStates.get(0), States.get(0));

        verify(StateRepository, times(1)).findAll();
    }

    @Test(expected = Exception.class)
    public void getAllException() throws ResourceNotExistException, ResourceNotExistException, Exception {
        when(StateRepository.findAll()).thenThrow((Class<? extends Throwable>) null);
        List<State> returnedStates= service.getAll();
    }
}
