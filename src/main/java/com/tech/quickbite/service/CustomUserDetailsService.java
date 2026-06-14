package com.tech.quickbite.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tech.quickbite.entity.User;
import com.tech.quickbite.repository.UserRepository;
import com.tech.quickbite.security.CustomUserDetails;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Optional<User> optionalUser =
                userRepository.findByEmail(email);

        if(optionalUser.isEmpty()) {

            throw new UsernameNotFoundException(
                    "User not found");
        }

        return new CustomUserDetails(
                optionalUser.get());
    }
}