package com.adminapp.services;

import com.adminapp.dtos.UserRolDto;
import com.adminapp.models.RolModel;
import com.adminapp.models.UserModel;
import com.adminapp.models.UserRoleModel;
import com.adminapp.models.UserRolePkModel;
import com.adminapp.repositories.RolRepository;
import com.adminapp.repositories.UserRepository;
import com.adminapp.repositories.UserRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserRolRepository userRolRepository;

    public ResponseEntity getUsers(){
        List<UserModel> listUser = userRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listUser);
    }

    public ResponseEntity newUser(UserModel user){
        System.out.println(user.toString());
        userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    public ResponseEntity getUsersRoles(){
        List<UserRoleModel> listUser = userRolRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listUser);
    }

    public ResponseEntity newUserRole(UserRolDto userRole){

        UserRolePkModel userRolPk = new UserRolePkModel();
        userRolPk.setIdRol(userRole.getIdRol());
        userRolPk.setIdUser(userRole.getIdUser());

        Optional<RolModel> optRol = rolRepository.findById(userRole.getIdRol());
        Optional<UserModel> optUser = userRepository.findById(userRole.getIdUser());

        UserRoleModel newUserModel = new UserRoleModel();
        newUserModel.setId(userRolPk);
        newUserModel.setUser(optUser.get());
        newUserModel.setRol(optRol.get());
        newUserModel.setStatus(userRole.getStatus());
        System.out.println(newUserModel.toString());
        newUserModel = userRolRepository.save(newUserModel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newUserModel);
    }

}
