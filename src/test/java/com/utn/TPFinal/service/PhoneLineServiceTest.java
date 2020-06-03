package com.utn.TPFinal.service;

import com.utn.TPFinal.model.entities.PhoneLine;
import com.utn.TPFinal.repositories.IPhoneLineRepository;
import com.utn.TPFinal.services.PhoneLineService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhoneLineServiceTest {

    PhoneLineService service;

    @Mock
    IPhoneLineRepository phoneLineRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new PhoneLineService(phoneLineRepository);
    }

    @Test
    public void TestGetCantCellphoneLinesOK(){

        List<PhoneLine> phonelines= new ArrayList<>();

        PhoneLine phoneline = new PhoneLine();
        phoneline.setId(0);
        phoneline.setNumberLine(1111);
        PhoneLine phoneline2 = new PhoneLine();
        phoneline.setId(0);
        phoneline.setNumberLine(1111);

        phonelines.add(phoneline);
        phonelines.add(phoneline2);

        when(phoneLineRepository.getPhoneLineByPhoneLineType("MOVIL")).thenReturn(phonelines);
        Integer cellphones = service.getCantCellphoneLines();

        assertNotNull(cellphones);
        assertTrue(cellphones == phonelines.size());

        verify(phoneLineRepository, times(1)).getPhoneLineByPhoneLineType("MOVIL");
    }


}