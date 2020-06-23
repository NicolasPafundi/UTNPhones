package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.repositories.IUserTypeRepository;
import com.utn.TPFinal.services.UserTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserTypeServiceTest {

    UserTypeService service;

    @Mock
    IUserTypeRepository UserTypeRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new UserTypeService(UserTypeRepository);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        UserType UserType = new UserType();
        UserType.setId(1);
        UserType.setName(UserTypes.EMPLOYEE);

        List<UserType> UserTypes = new ArrayList<>();
        UserTypes.add(UserType);

        when(UserTypeRepository.findAll()).thenReturn(UserTypes);
        List<UserType> returnedUserTypes= service.getAll();

        assertEquals(returnedUserTypes.size(), 1);
        assertEquals(returnedUserTypes.get(0), UserTypes.get(0));

        verify(UserTypeRepository, times(1)).findAll();
    }
}
