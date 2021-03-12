package com.example.userserver.service;

import com.example.userserver.model.User;
import com.example.userserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username){
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid username");
        }
        return user;
    }
}
