package com.adminapp.services;

import com.adminapp.dtos.AuthenticationRequest;
import com.adminapp.dtos.AuthenticationResponse;
import com.adminapp.models.UserModel;
import com.adminapp.repositories.UserRepository;
import com.adminapp.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    public ResponseEntity authenticateUser(AuthenticationRequest authData){
        //logger.warn("Password "+authData.getPassword());
        //logger.warn("Password encrypt"+ passwordEncoder.encode(authData.getPassword()));
        logger.info("Authenticating user "+authData.getUsername());
        try{
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authData.getUsername(), authData.getPassword())
            );
        }catch (BadCredentialsException e){
            logger.warn(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }

        final UserDetails userDetails = loadUserByUsername(authData.getUsername());
        final String token = jwtUtils.generateToken(userDetails, authData.getIp());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AuthenticationResponse(token));
    }

}
