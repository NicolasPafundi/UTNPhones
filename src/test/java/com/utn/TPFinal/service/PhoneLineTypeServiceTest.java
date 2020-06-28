package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.Enum.PhoneLineTypes;
import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.repositories.IPhoneLineTypeRepository;
import com.utn.TPFinal.repositories.IPhoneLineTypeRepository;
import com.utn.TPFinal.services.PhoneLineTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhoneLineTypeServiceTest {

    PhoneLineTypeService service;

    @Mock
    IPhoneLineTypeRepository PhoneLineTypeRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new PhoneLineTypeService(PhoneLineTypeRepository);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        PhoneLineType PhoneLineType = new PhoneLineType();
        PhoneLineType.setId(1);
        PhoneLineType.setName(PhoneLineTypes.MOVIL);

        List<PhoneLineType> PhoneLineTypes = new ArrayList<>();
        PhoneLineTypes.add(PhoneLineType);

        when(PhoneLineTypeRepository.findAll()).thenReturn(PhoneLineTypes);
        List<PhoneLineType> returnedPhoneLineTypes= service.getAll();

        assertEquals(returnedPhoneLineTypes.size(), 1);
        assertEquals(returnedPhoneLineTypes.get(0), PhoneLineTypes.get(0));

        verify(PhoneLineTypeRepository, times(1)).findAll();
    }

    @Test(expected = Exception.class)
    public void getAllException() throws ResourceNotExistException, ResourceNotExistException, Exception {
        when(PhoneLineTypeRepository.findAll()).thenThrow((Class<? extends Throwable>) null);
        List<PhoneLineType> returnedPhoneLineTypes= service.getAll();
    }
}
