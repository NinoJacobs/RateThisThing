package com.ratethisthing.ratethisthing.service;

import com.ratethisthing.ratethisthing.entity.User;
import com.ratethisthing.ratethisthing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }
}
