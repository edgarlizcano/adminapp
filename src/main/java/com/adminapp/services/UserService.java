package com.adminapp.services;

import com.adminapp.dtos.AuthenticationRequest;
import com.adminapp.dtos.UserRolDto;
import com.adminapp.models.RolModel;
import com.adminapp.models.UserModel;
import com.adminapp.models.UserRoleModel;
import com.adminapp.models.UserRolePkModel;
import com.adminapp.repositories.RolRepository;
import com.adminapp.repositories.UserRepository;
import com.adminapp.repositories.UserRolRepository;
import com.adminapp.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserRolRepository userRolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optUser = userRepository.findByUserName(username);
        if(optUser.isPresent()){
            UserModel user = optUser.get();
            UserDetails userDetail = new User(user.getUserName(),user.getPassword(), new ArrayList<>());
            return  userDetail;
        }
        return null;
    }

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
