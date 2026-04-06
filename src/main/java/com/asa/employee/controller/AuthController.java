package com.asa.employee.controller;

import com.asa.employee.entity.User;
import com.asa.employee.exceptions.InternalServerErrorException;
import com.asa.employee.exceptions.InvalidRequestException;
import com.asa.employee.exceptions.UnAuthorizedException;
import com.asa.employee.model.AuthRequest;
import com.asa.employee.repository.UserRepository;
import com.asa.employee.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@RestController
@RequestMapping("/auth") // This sets the "http://localhost:8080/auth" part
public class AuthController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login") // This sets the "/login" part
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // 1. Verify the username and password against the database
            Optional<User> byUsernameAndPassword = userRepository.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
            if(byUsernameAndPassword.isPresent()){
                // 2. If authentication is successful, generate the JWT
                String token = jwtUtils.generateToken(authRequest.getUsername());

                // 3. Return the token to the client
                return ResponseEntity.ok(token);
            }else{
                throw new UnAuthorizedException("Invalid Username or Password");
            }

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }


    }
}
