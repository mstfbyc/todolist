package com.smartpro.controller;

import com.smartpro.entity.Users;
import com.smartpro.repository.UserRepository;
import com.smartpro.request.LoginRequest;
import com.smartpro.request.RegistirationRequest;
import com.smartpro.response.TokenResponse;
import com.smartpro.security.JwtTokenUtil;
import com.smartpro.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class AccountController {

    @Autowired
    private DaoAuthenticationProvider authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<TokenResponse>login(@RequestBody LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        final Users user = userRepository.findByUsername(request.getUsername()).orElse(null);
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(request.getUsername(), token));
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  ResponseEntity<Boolean> register(@RequestBody RegistirationRequest request){
        Boolean response = userService.register(request);
        return ResponseEntity.ok(response);
    }
}
