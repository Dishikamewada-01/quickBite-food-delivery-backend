package com.tech.quickbite.service.impl;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tech.quickbite.entity.User;
import com.tech.quickbite.exception.ResourceNotFoundException;
import com.tech.quickbite.repository.UserRepository;
import com.tech.quickbite.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        Optional<User> user =
                userRepository.findByEmail(email);

        return user.orElseThrow(
                () -> new ResourceNotFoundException(
                        "User not found"));
    }
}