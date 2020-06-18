package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.StateController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.entities.State;
import com.utn.TPFinal.services.StateService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class StateControllerTest {

    StateController controller;
    StateService service;

    @Before
    public void setUp() {
        service = mock(StateService.class);
        controller = new StateController(service);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, Exception {
        State State = new State();
        State.setId(1);
        List<State> States= new ArrayList<>();
        States.add(State);

        when(service.getAll()).thenReturn(States);
        ResponseEntity<List<State>> returnedStates= controller.getAll("1");

        assertEquals(returnedStates.getBody().size(), 1);
        assertEquals(returnedStates.getBody().get(0), States.get(0));

        verify(service, times(1)).getAll();
    }
}
