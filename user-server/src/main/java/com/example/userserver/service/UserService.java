package com.example.userserver.service;

import com.example.userserver.model.User;
import com.example.userserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username){
        User uwu = userRepository.findUserByUsername(username);
        System.out.println(userRepository.findById(uwu.getId()).get().getRole());
        return uwu;
    }
}
