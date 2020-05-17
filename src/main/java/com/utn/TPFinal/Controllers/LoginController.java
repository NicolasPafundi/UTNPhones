package com.utn.TPFinal.Controllers;

import com.utn.TPFinal.Domain.DTOs.LoginInput;
import com.utn.TPFinal.Domain.Entities.User;
import com.utn.TPFinal.Exceptions.InvalidLoginException;
import com.utn.TPFinal.Exceptions.UserNotexistException;
import com.utn.TPFinal.Exceptions.ValidationException;
import com.utn.TPFinal.Services.UserService;
import com.utn.TPFinal.Session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    UserService userService;
    SessionManager sessionManager;

    @Autowired
    public LoginController(UserService userService, SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginInput loginInput) throws InvalidLoginException, ValidationException {
        ResponseEntity response;
        try {
            User u = userService.GetByUserNameAndPassword(loginInput);
            String token = sessionManager.createSession(u);
            response = ResponseEntity.ok().headers(createHeaders(token)).build();
        } catch (UserNotexistException e) {
            throw new InvalidLoginException(e);
        }
        return response;
    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }
}