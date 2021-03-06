package com.example.edgeserver.service;

import com.example.edgeserver.client.UserClient;
import com.example.edgeserver.controller.dto.UserDTO;
import com.example.edgeserver.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDTO user = userClient.getUser(username);
            if(user == null) throw new UsernameNotFoundException("User does not exist");

            return new CustomUserDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User does not exist");
        }
    }
}
