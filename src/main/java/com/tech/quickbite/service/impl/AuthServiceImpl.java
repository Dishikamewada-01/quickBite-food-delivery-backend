package com.tech.quickbite.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.quickbite.dto.request.LoginRequest;
import com.tech.quickbite.dto.request.RegisterRequest;
import com.tech.quickbite.dto.response.AuthResponse;
import com.tech.quickbite.entity.User;
import com.tech.quickbite.enums.Role;
import com.tech.quickbite.exception.ResourceAlreadyExistsException;
import com.tech.quickbite.repository.UserRepository;
import com.tech.quickbite.security.JwtService;
import com.tech.quickbite.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    "Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(Role.CUSTOMER);

        userRepository.save(user);

        String token =
                jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        Optional<User> optionalUser =
                userRepository.findByEmail(
                        request.getEmail());

        User user = optionalUser.get();

        String token =
                jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}