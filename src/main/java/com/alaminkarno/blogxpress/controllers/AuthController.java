package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.payloads.JWTAuthResponse;
import com.alaminkarno.blogxpress.payloads.JwtAuthRequest;
import com.alaminkarno.blogxpress.security.JWTTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JwtAuthRequest request){

       this.authenticate(request.getUsername(),request.getPassword());

       UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
       String token = this.jwtTokenHelper.generateToken(userDetails);

       JWTAuthResponse response = new JWTAuthResponse();
       response.setToken(token);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username,String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
}
