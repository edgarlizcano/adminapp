package com.adminapp.controllers;

import com.adminapp.models.RolModel;
import com.adminapp.models.UserModel;
import com.adminapp.services.RolService;
import com.adminapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "roles")
public class RolController {

    @Autowired
    RolService rolService;

    @GetMapping("getRoles")
    public ResponseEntity getRoles(){
        return rolService.getRoles();
    }

    @PostMapping("newRol")
    public ResponseEntity newRol(@RequestBody RolModel rol){
        return rolService.newRol(rol);
    }

}
