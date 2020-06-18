package com.utn.TPFinal.controller;

import com.utn.TPFinal.controllers.UserController;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.services.UserService;
import com.utn.TPFinal.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    UserController controller;
    UserService service;
    SessionManager sessionManagerService;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Before
    public void setUp() {
        service = mock(UserService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new UserController(service,sessionManagerService);
    }

    @Test
    public void getAllOk() throws ResourceNotExistException, Exception {
        User User = new User();
        User.setId(1);
        User.setFirstName("name");
        User.setLastName("lastName");
        List<User> Users= new ArrayList<>();
        Users.add(User);

        when(service.getAll()).thenReturn(Users);
        ResponseEntity<List<User>> returnedUsers= controller.getAll("1");

        assertEquals(returnedUsers.getBody().size(), 1);
        assertEquals(returnedUsers.getBody().get(0), Users.get(0));

        verify(service, times(1)).getAll();
    }

    @Test
    public void getByIdOk() throws ResourceNotExistException, Exception {
        User User = new User();
        User.setId(1);
        User.setFirstName("name");
        User.setLastName("lastName");

        when(service.getById(1)).thenReturn(User);
        ResponseEntity<User> returnedUser= controller.getById("1",1);

        assertNotNull(returnedUser);
        assertEquals(returnedUser.getBody(), User);

        verify(service, times(1)).getById(1);
    }

    @Test
    public void getAllByUserTypeOk() throws ResourceNotExistException, Exception {
        User User = new User();
        User.setId(1);
        User.setFirstName("name");
        User.setLastName("lastName");
        List<User> Users= new ArrayList<>();
        Users.add(User);

        when(service.getAllByUserType(1)).thenReturn(Users);
        ResponseEntity<List<User>> returnedUsers= controller.getAllByUserType("1",1);

        assertEquals(returnedUsers.getBody().size(), 1);
        assertEquals(returnedUsers.getBody().get(0), Users.get(0));

        verify(service, times(1)).getAllByUserType(1);
    }

    @Test
    public void getByCurrentUserOk() throws ResourceNotExistException, Exception {
        User User = new User();
        User.setId(1);
        User.setFirstName("name");
        User.setLastName("lastName");


        when(sessionManagerService.getCurrentUser("1")).thenReturn(User);
        when(service.getById(1)).thenReturn(User);
        ResponseEntity<User> returnedUsers= controller.getByCurrentUser("1");

        assertNotNull(returnedUsers.getBody());
        assertEquals(returnedUsers.getBody(), User);

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).getById(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, Exception, ValidationException {
        User User = new User();
        User.setId(1);
        User.setFirstName("name");
        User.setLastName("lastName");

        doNothing().when(service).update(User);
        ResponseEntity returned= controller.update("1",User);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(service, times(1)).update(User);
    }

    @Test
    public void updateCurrentUserOk() throws ResourceNotExistException, Exception, ValidationException {
        User User = new User();
        User.setId(1);
        User.setFirstName("name");
        User.setLastName("lastName");

        when(sessionManagerService.getCurrentUser("1")).thenReturn(User);
        doNothing().when(service).update(User);
        ResponseEntity returned= controller.updateCurrentUser("1",User);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).update(User);
    }

    @Test
    public void removeOk() throws ResourceNotExistException, Exception {

        doNothing().when(service).remove(1);
        ResponseEntity returned= controller.remove("1",1);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(service, times(1)).remove(1);
    }

    @Test
    public void removeCurrentUserOk() throws ResourceNotExistException, Exception {
        User User = new User();
        User.setId(1);
        User.setFirstName("name");
        User.setLastName("lastName");

        when(sessionManagerService.getCurrentUser("1")).thenReturn(User);
        doNothing().when(service).remove(1);
        ResponseEntity returned= controller.removeCurrentUser("1");

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(sessionManagerService, times(1)).getCurrentUser("1");
        verify(service, times(1)).remove(1);
    }
}
