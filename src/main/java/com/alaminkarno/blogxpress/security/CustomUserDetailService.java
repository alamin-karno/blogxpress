package com.alaminkarno.blogxpress.security;

import com.alaminkarno.blogxpress.entities.User;
import com.alaminkarno.blogxpress.exceptions.ResourceNotFoundException;
import com.alaminkarno.blogxpress.repositories.UserRepository;
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
        // LOAD USER FROM DATABASE BY USERNAME
        return this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User","email : "+username,0));
    }
}
