package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.dtos.CallInput;
import com.utn.TPFinal.model.dtos.CallsReportFilter;
import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.InfraResponse;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.ReportCallsByUserByDate;
import com.utn.TPFinal.repositories.ICallRepository;
import com.utn.TPFinal.repositories.ICallRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import com.utn.TPFinal.services.CallService;
import com.utn.TPFinal.services.CallService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallServiceTest {

    CallService service;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Mock
    ICallRepository CallRepository;
    @Mock
    IUserRepository userRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new CallService(CallRepository,userRepository);
    }

    @Test
    public void GetAllOk() throws ResourceNotExistException, ResourceNotExistException {
        Call Call = new Call();
        Call.setId(1);
        Call.setAmount(1);
        Call.setDuration(1);
        Call.setCreatedOn(new Date());

        List<Call> Calls = new ArrayList<>();
        Calls.add(Call);

        when(CallRepository.findAll()).thenReturn(Calls);
        List<Call> returnedCalls= service.getAll();

        assertEquals(returnedCalls.size(), 1);
        assertEquals(returnedCalls.get(0), Calls.get(0));

        verify(CallRepository, times(1)).findAll();
    }

    @Test
    public void getByUserIDOk() throws ResourceNotExistException, ResourceNotExistException {
        Call Call = new Call();
        Call.setId(1);
        Call.setAmount(1);
        Call.setDuration(1);
        Call.setCreatedOn(new Date());

        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        List<Call> Calls = new ArrayList<>();
        Calls.add(Call);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(CallRepository.getByUserId(1)).thenReturn(Calls);
        List<Call> returnedCalls= service.getByUserId(1);

        assertEquals(returnedCalls.size(), 1);
        assertEquals(returnedCalls.get(0), Calls.get(0));

        verify(userRepository, times(1)).findById(1);
        verify(CallRepository, times(1)).getByUserId(1);
    }

    @Test
    public void getByIdOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        Call Call = new Call();
        Call.setId(1);
        Call.setAmount(1);
        Call.setDuration(1);
        Call.setCreatedOn(new Date());

        when(CallRepository.findById(1)).thenReturn(java.util.Optional.of(Call));
        Call returnedCall= service.getById(1);

        assertNotNull(returnedCall);
        assertEquals(returnedCall, Call);

        verify(CallRepository, times(1)).findById(1);
    }

    @Test
    public void addOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        CallInput callInput = new CallInput();
        callInput.setNumberTo(1);
        callInput.setNumberFrom(1);
        callInput.setDuration(1);
        callInput.setCallDate(new Date());

        InfraResponse infraResponse = factory.createProjection(InfraResponse.class);
        infraResponse.setMessage("test");
        infraResponse.setCreatedOn(new Date());
        infraResponse.setCallId(1);

        when(CallRepository.createCall(callInput.getNumberFrom(),callInput.getNumberTo(),callInput.getDuration(),callInput.getCallDate())).thenReturn(infraResponse);
        InfraResponse returnedInfraResponse= service.createCall(callInput);

        assertNotNull(returnedInfraResponse);
        assertEquals(returnedInfraResponse.getCallId(), infraResponse.getCallId());
        assertEquals(returnedInfraResponse.getMessage(), infraResponse.getMessage());
        assertEquals(returnedInfraResponse.getCreatedOn(), infraResponse.getCreatedOn());

        verify(CallRepository, times(1)).createCall(callInput.getNumberFrom(),callInput.getNumberTo(),callInput.getDuration(),callInput.getCallDate());
    }

    @Test
    public void removeOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        Call Call = new Call();
        Call.setId(1);
        Call.setAmount(1);
        Call.setDuration(1);
        Call.setCreatedOn(new Date());

        when(CallRepository.findById(1)).thenReturn(java.util.Optional.of(Call));
        doNothing().when(CallRepository).deleteById(1);
        service.remove(1);

        verify(CallRepository, times(1)).findById(1);
        verify(CallRepository, times(1)).deleteById(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        Call Call = new Call();
        Call.setId(1);
        Call.setAmount(1);
        Call.setDuration(1);
        Call.setCreatedOn(new Date());


        when(CallRepository.findById(1)).thenReturn(java.util.Optional.of(Call));
        when(CallRepository.save(Call)).thenReturn(Call);
        service.update(Call);

        verify(CallRepository, times(1)).findById(1);
        verify(CallRepository, times(1)).save(Call);
    }

    @Test
    public void getReportCallsByUserByDateOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        CallsReportFilter callsReportFilter = new CallsReportFilter();
        callsReportFilter.setUserId(1);
        callsReportFilter.setDateTo(new Date());
        callsReportFilter.setDateFrom(new Date());

        List<ReportCallsByUserByDate> reportCallsByUserByDateList = new ArrayList<>();

        ReportCallsByUserByDate reportCallsByUserByDate = factory.createProjection(ReportCallsByUserByDate.class);
        reportCallsByUserByDate.setTotalAmount(1);
        reportCallsByUserByDate.setNumeroOrigen("1");
        reportCallsByUserByDate.setNumeroDestino("1");
        reportCallsByUserByDate.setMinDuration(1);
        reportCallsByUserByDate.setFechaLlamada(new Date());
        reportCallsByUserByDate.setCiudadOrigen("1");
        reportCallsByUserByDate.setCiudadDestino("1");

        reportCallsByUserByDateList.add(reportCallsByUserByDate);

        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(CallRepository.getReportCallsByUserByDate(callsReportFilter.getUserId(),callsReportFilter.getDateFrom(),callsReportFilter.getDateTo())).thenReturn(reportCallsByUserByDateList);
        List<ReportCallsByUserByDate> reponseReportCallsByUserByDateList= service.getReportCallsByUserByDate(callsReportFilter);

        assertNotNull(reponseReportCallsByUserByDateList);
        assertEquals(reponseReportCallsByUserByDateList.size(), reponseReportCallsByUserByDateList.size());
        assertEquals(reponseReportCallsByUserByDateList.get(0), reponseReportCallsByUserByDateList.get(0));

        verify(userRepository, times(1)).findById(1);
        verify(CallRepository, times(1)).getReportCallsByUserByDate(callsReportFilter.getUserId(),callsReportFilter.getDateFrom(),callsReportFilter.getDateTo());
    }
}
