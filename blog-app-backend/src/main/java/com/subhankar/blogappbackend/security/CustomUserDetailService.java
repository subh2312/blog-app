package com.subhankar.blogappbackend.security;

import com.subhankar.blogappbackend.entities.User;
import com.subhankar.blogappbackend.exceptions.ResourceNotFoundException;
import com.subhankar.blogappbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email",username));
    }
}
