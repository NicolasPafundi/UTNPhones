package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.model.dtos.MobileReportFilter;
import com.utn.TPFinal.model.entities.Bill;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.repositories.IBillRepository;
import com.utn.TPFinal.repositories.IMobileReportRepository;
import com.utn.TPFinal.repositories.IUserRepository;
import com.utn.TPFinal.services.BillService;
import com.utn.TPFinal.services.MobileReportService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class BillServiceTest {

    BillService service;

    @Mock
    IBillRepository billRepository;
    @Mock
    IUserRepository userRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new BillService(billRepository,userRepository);
    }

    @Test
    public void GetAllOk() throws ResourceNotExistException, ResourceNotExistException {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setCallsAmount(1);
        bill.setCreatedOn(new Date());
        bill.setPaid(false);
        bill.setPriceCost(1);
        bill.setPriceFinal(1);

        List<Bill> bills = new ArrayList<>();
        bills.add(bill);

        when(billRepository.findAll()).thenReturn(bills);
        List<Bill> returnedBills= service.getAll();

        assertEquals(returnedBills.size(), 1);
        assertEquals(returnedBills.get(0), bills.get(0));

        verify(billRepository, times(1)).findAll();
    }

    @Test
    public void getByUserIDOk() throws ResourceNotExistException, ResourceNotExistException {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setCallsAmount(1);
        bill.setCreatedOn(new Date());
        bill.setPaid(false);
        bill.setPriceCost(1);
        bill.setPriceFinal(1);

        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        List<Bill> bills = new ArrayList<>();
        bills.add(bill);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(billRepository.findByUserId(1)).thenReturn(bills);
        List<Bill> returnedBills= service.getByUserID(1);

        assertEquals(returnedBills.size(), 1);
        assertEquals(returnedBills.get(0), bills.get(0));

        verify(userRepository, times(1)).findById(1);
        verify(billRepository, times(1)).findByUserId(1);
    }

    @Test
    public void getByIdOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setCallsAmount(1);
        bill.setCreatedOn(new Date());
        bill.setPaid(false);
        bill.setPriceCost(1);
        bill.setPriceFinal(1);

        when(billRepository.findById(1)).thenReturn(java.util.Optional.of(bill));
        Bill returnedBill= service.getById(1);

        assertNotNull(returnedBill);
        assertEquals(returnedBill, bill);

        verify(billRepository, times(1)).findById(1);
    }

    @Test
    public void addOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setCallsAmount(1);
        bill.setCreatedOn(new Date());
        bill.setPaid(false);
        bill.setPriceCost(1);
        bill.setPriceFinal(1);

        when(billRepository.existsById(1)).thenReturn(false);
        when(billRepository.save(bill)).thenReturn(bill);
        int returnedId= service.add(bill);

        assertNotNull(returnedId);
        assertEquals(1, returnedId);

        verify(billRepository, times(1)).existsById(1);
        verify(billRepository, times(1)).save(bill);
    }

    @Test
    public void removeOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setCallsAmount(1);
        bill.setCreatedOn(new Date());
        bill.setPaid(false);
        bill.setPriceCost(1);
        bill.setPriceFinal(1);

        when(billRepository.findById(1)).thenReturn(java.util.Optional.of(bill));
        doNothing().when(billRepository).deleteById(1);
        service.remove(1);

        verify(billRepository, times(1)).findById(1);
        verify(billRepository, times(1)).deleteById(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, ResourceNotExistException, Exception, ResourceAlreadyExistExeption {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setCallsAmount(1);
        bill.setCreatedOn(new Date());
        bill.setPaid(false);
        bill.setPriceCost(1);
        bill.setPriceFinal(1);

        when(billRepository.findById(1)).thenReturn(java.util.Optional.of(bill));
        when(billRepository.save(bill)).thenReturn(bill);
        service.update(bill);

        verify(billRepository, times(1)).findById(1);
        verify(billRepository, times(1)).save(bill);
    }
}
