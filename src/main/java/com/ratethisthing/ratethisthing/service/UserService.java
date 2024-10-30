package com.ratethisthing.ratethisthing.service;

import com.ratethisthing.ratethisthing.entity.User;
import com.ratethisthing.ratethisthing.mappers.UserMapper;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.UserDTO;
import com.ratethisthing.ratethisthing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username){
        return userRepository.findByUsername(username);
    }

    public UserDTO registerUser(UserDTO userDTO) {
        // TODO
        // check if user exists
        // if exists - throw new DataIntegrityViolationException("User already exists");
        // else save user


        User user = UserMapper.INSTANCE.userDtoToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.userEntityToDto(savedUser);
    }
}
