package com.adminapp.controllers;

import com.adminapp.dtos.AuthenticationRequest;
import com.adminapp.dtos.UserRolDto;
import com.adminapp.services.AuthenticationService;
import com.adminapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("authenticate")
    public ResponseEntity authenticateUser(@RequestBody AuthenticationRequest authData){
        return authenticationService.authenticateUser(authData);
    }

}
