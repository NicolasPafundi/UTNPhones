package com.utn.TPFinal.service;

import com.utn.TPFinal.exceptions.InvalidLoginException;
import com.utn.TPFinal.exceptions.ResourceAlreadyExistExeption;
import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.Enum.UserTypes;
import com.utn.TPFinal.model.dtos.LoginInput;
import com.utn.TPFinal.model.entities.User;
import com.utn.TPFinal.model.entities.UserType;
import com.utn.TPFinal.repositories.IUserRepository;
import com.utn.TPFinal.repositories.IUserTypeRepository;
import com.utn.TPFinal.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    UserService service;

    @Mock
    IUserRepository UserRepository;
    @Mock
    IUserTypeRepository userTypeRepository;

    @Before
    public void setUp() {
        initMocks(this);
        service = new UserService(UserRepository,userTypeRepository);
    }

    @Test
    public void GetAllOk() {
        User User = new User();
        User.setId(1);

        List<User> Users = new ArrayList<>();
        Users.add(User);

        when(UserRepository.findAll()).thenReturn(Users);
        List<User> returnedUsers= service.getAll();

        assertEquals(returnedUsers.size(), 1);
        assertEquals(returnedUsers.get(0), Users.get(0));

        verify(UserRepository, times(1)).findAll();
    }

    @Test
    public void getAllByUserTypeOk() throws ResourceNotExistException, Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        UserType UserType = new UserType();
        UserType.setId(1);
        UserType.setName(UserTypes.CLIENT);

        List<User> Users = new ArrayList<>();
        Users.add(user);

        when(UserRepository.getAllByUserType(1)).thenReturn(Users);
        when(userTypeRepository.findById(1)).thenReturn(java.util.Optional.of(UserType));
        List<User> returnedUsers= service.getAllByUserType(1);

        assertEquals(returnedUsers.size(), 1);
        assertEquals(returnedUsers.get(0), Users.get(0));

        verify(UserRepository, times(1)).getAllByUserType(1);
        verify(userTypeRepository, times(1)).findById(1);
    }

    @Test
    public void getByIdOk() throws ResourceNotExistException, ResourceNotExistException, Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        when(UserRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        User returnedUser= service.getById(1);

        assertNotNull(returnedUser);
        assertEquals(returnedUser, user);

        verify(UserRepository, times(1)).findById(1);
    }

    @Test
    public void getByUserNameAndPassword() throws ValidationException, InvalidLoginException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        LoginInput loginInput = new LoginInput();
        loginInput.setUserName("test");
        loginInput.setPassword("test");

        when(UserRepository.getByUserNameAndPassword("test","test")).thenReturn(user);
        User returnedUser= service.getByUserNameAndPassword(loginInput);

        assertNotNull(returnedUser);
        assertEquals(returnedUser, user);

        verify(UserRepository, times(1)).getByUserNameAndPassword("test","test");
    }

    @Test
    public void addOk() throws Exception, ResourceAlreadyExistExeption, ValidationException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        UserType UserType = new UserType();
        UserType.setId(1);
        UserType.setName(UserTypes.CLIENT);
        user.setUserType(UserType);

        when(UserRepository.existsById(1)).thenReturn(false);
        when(UserRepository.save(user)).thenReturn(user);
        int returnedId= service.add(user);

        assertNotNull(returnedId);
        assertEquals(1, returnedId);

        verify(UserRepository, times(1)).existsById(1);
        verify(UserRepository, times(1)).save(user);
    }

    @Test
    public void removeOk() throws ResourceNotExistException, Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");

        when(UserRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        doNothing().when(UserRepository).deleteById(1);
        service.remove(1);

        verify(UserRepository, times(1)).findById(1);
        verify(UserRepository, times(1)).deleteById(1);
    }

    @Test
    public void updateOk() throws ResourceNotExistException, Exception, ValidationException {
        User user = new User();
        user.setId(1);
        user.setFirstName("name");
        user.setLastName("lastName");
        UserType UserType = new UserType();
        UserType.setId(1);
        UserType.setName(UserTypes.CLIENT);
        user.setUserType(UserType);

        when(UserRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(UserRepository.save(user)).thenReturn(user);
        service.update(user);

        verify(UserRepository, times(1)).findById(1);
        verify(UserRepository, times(1)).save(user);
    }
}
