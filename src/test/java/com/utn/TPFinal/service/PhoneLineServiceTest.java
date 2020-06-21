package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.PhoneLineTypes;
import com.utn.TPFinal.model.entities.PhoneLine;
import com.utn.TPFinal.model.entities.PhoneLineType;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.repositories.IPhoneLineRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import com.utn.TPFinal.services.PhoneLineService;
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

public class PhoneLineServiceTest {

    PhoneLineService service;

    @Mock
    IPhoneLineRepository PhoneLineRepository;
    @Mock
    IUserRepository userRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new PhoneLineService(PhoneLineRepository,userRepository);
    }

    @Test
    public void GetAllOk() throws ResourceNotExistException, ResourceNotExistException {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        PhoneLine.setNumberLine(1);
        PhoneLine.setActive(true);

        List<PhoneLine> PhoneLines = new ArrayList<>();
        PhoneLines.add(PhoneLine);

        when(PhoneLineRepository.findAll()).thenReturn(PhoneLines);
        List<PhoneLine> returnedPhoneLines= service.getAll();

        assertEquals(returnedPhoneLines.size(), 1);
        assertEquals(returnedPhoneLines.get(0), PhoneLines.get(0));

        verify(PhoneLineRepository, times(1)).findAll();
    }

    @Test
    public void getByUserIDOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        PhoneLine.setNumberLine(1);
        PhoneLine.setActive(true);

        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        List<PhoneLine> PhoneLines = new ArrayList<>();
        PhoneLines.add(PhoneLine);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(PhoneLineRepository.getByUser(1)).thenReturn(PhoneLines);
        List<PhoneLine> returnedPhoneLines= service.getByUser(1);

        assertEquals(returnedPhoneLines.size(), 1);
        assertEquals(returnedPhoneLines.get(0), PhoneLines.get(0));

        verify(userRepository, times(1)).findById(1);
        verify(PhoneLineRepository, times(1)).getByUser(1);
    }

    @Test
    public void getByIdOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        PhoneLine.setNumberLine(1);
        PhoneLine.setActive(true);

        when(PhoneLineRepository.findById(1)).thenReturn(java.util.Optional.of(PhoneLine));
        PhoneLine returnedPhoneLine= service.getById(1);

        assertNotNull(returnedPhoneLine);
        assertEquals(returnedPhoneLine, PhoneLine);

        verify(PhoneLineRepository, times(1)).findById(1);
    }

    @Test
    public void addOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption, ValidationException {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        PhoneLine.setNumberLine(1);
        PhoneLine.setActive(true);
        PhoneLineType phoneLineType = new PhoneLineType();
        phoneLineType.setId(1);
        phoneLineType.setName(PhoneLineTypes.MOVIL);
        PhoneLine.setPhoneLineType(phoneLineType);

        when(PhoneLineRepository.existsById(1)).thenReturn(false);
        when(PhoneLineRepository.save(PhoneLine)).thenReturn(PhoneLine);
        int returnedId= service.add(PhoneLine);

        assertNotNull(returnedId);
        assertEquals(1, returnedId);

        verify(PhoneLineRepository, times(1)).existsById(1);
        verify(PhoneLineRepository, times(1)).save(PhoneLine);
    }

    @Test
    public void removeOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        PhoneLine.setNumberLine(1);
        PhoneLine.setActive(true);

        when(PhoneLineRepository.findById(1)).thenReturn(java.util.Optional.of(PhoneLine));
        doNothing().when(PhoneLineRepository).deleteById(1);
        service.remove(1);

        verify(PhoneLineRepository, times(1)).findById(1);
        verify(PhoneLineRepository, times(1)).deleteById(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption, ValidationException {
        PhoneLine PhoneLine = new PhoneLine();
        PhoneLine.setId(1);
        PhoneLine.setNumberLine(1);
        PhoneLine.setActive(true);
        PhoneLineType phoneLineType = new PhoneLineType();
        phoneLineType.setId(1);
        phoneLineType.setName(PhoneLineTypes.MOVIL);
        PhoneLine.setPhoneLineType(phoneLineType);

        when(PhoneLineRepository.findById(1)).thenReturn(java.util.Optional.of(PhoneLine));
        when(PhoneLineRepository.save(PhoneLine)).thenReturn(PhoneLine);
        service.update(PhoneLine);

        verify(PhoneLineRepository, times(1)).findById(1);
        verify(PhoneLineRepository, times(1)).save(PhoneLine);
    }
}
