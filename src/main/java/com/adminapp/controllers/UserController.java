package com.adminapp.controllers;

import com.adminapp.dtos.UserRolDto;
import com.adminapp.models.UserModel;
import com.adminapp.models.UserRoleModel;
import com.adminapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getUsers")
    public ResponseEntity getUsers(){
        return userService.getUsers();
    }

    @PostMapping("newUser")
    public ResponseEntity newUser(@RequestBody UserModel user){
        return userService.newUser(user);
    }

    @GetMapping("getUsersRoles")
    public ResponseEntity getUsersRoles(){
        return userService.getUsersRoles();
    }

    @PostMapping("newUserRole")
    public ResponseEntity newUserRole(@RequestBody UserRolDto userRole){
        return userService.newUserRole(userRole);
    }

}
