package com.adminapp.services;

import com.adminapp.models.RolModel;
import com.adminapp.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public ResponseEntity getRoles(){
        List<RolModel> listRoles = rolRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listRoles);
    }

    public ResponseEntity newRol(RolModel rol){
        System.out.println(rol.toString());
        rolRepository.save(rol);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rol);
    }

}
