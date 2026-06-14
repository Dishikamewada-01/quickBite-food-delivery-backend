package com.tech.quickbite.service;

import com.tech.quickbite.dto.request.LoginRequest;
import com.tech.quickbite.dto.request.RegisterRequest;
import com.tech.quickbite.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}