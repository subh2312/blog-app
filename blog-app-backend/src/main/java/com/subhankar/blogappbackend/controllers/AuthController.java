package com.subhankar.blogappbackend.controllers;

import com.subhankar.blogappbackend.dto.UserDto;
import com.subhankar.blogappbackend.exceptions.InvalidCredentialException;
import com.subhankar.blogappbackend.payloads.JwtAuthRequest;
import com.subhankar.blogappbackend.payloads.JwtAuthResponse;
import com.subhankar.blogappbackend.security.JwtTokenHelper;
import com.subhankar.blogappbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper tokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());
        String token = tokenHelper.generateToken(userDetails);
        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setToken(token);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) throws Exception {
       UserDto user = userService.registerNewUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private void authenticate(String email, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        try{
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("Invalid Credentials");
            throw new InvalidCredentialException("Invalid UserName or Password");
        }



    }
}
