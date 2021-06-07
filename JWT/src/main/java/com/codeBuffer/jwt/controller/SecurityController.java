package com.codeBuffer.jwt.controller;

import com.codeBuffer.jwt.model.JwtRequest;
import com.codeBuffer.jwt.model.JwtResponse;
import com.codeBuffer.jwt.model.UserService;
import com.codeBuffer.jwt.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String login() {
        return "security passed";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            System.out.println("helo");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
            System.out.println("helo123");
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Creds ", e);
        }
        System.out.println("1223");
        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());
        final String token = jwtUtility.generateToken(userDetails);
        System.out.println("new JwtResponse(token) is "+ new JwtResponse(token));
        return new JwtResponse(token);
    }
}
