package com.task.flow.controllers;

import com.task.flow.dtos.LoginRequestDTO;

import com.task.flow.dtos.RegisterRequestDTO;
import com.task.flow.models.User;
import com.task.flow.security.JwtUtil;
import com.task.flow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");

        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        System.out.println("Usuario logueado: " + loginRequestDto.getUsername() + " - Se le ha proporcionado un token");

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDto) {
        if (userService.findByUsername(registerRequestDto.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(registerRequestDto.getPassword());
        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
