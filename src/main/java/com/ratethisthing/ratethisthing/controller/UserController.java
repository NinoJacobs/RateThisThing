package com.ratethisthing.ratethisthing.controller;

import com.ratethisthing.ratethisthing.entity.User;
import com.ratethisthing.ratethisthing.mappers.UserMapper;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.api.UserApi;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.UserCreated;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.UserDTO;
import com.ratethisthing.ratethisthing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> userEntities = userService.findAllUsers();

        if (userEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UserDTO> userList = userEntities.stream()
                .map(UserMapper.INSTANCE::userEntitytoDto)
                .toList();

        return ResponseEntity.ok(userList);
    }

    @Override
    public ResponseEntity<UserDTO> getUserByUsername(String username){
        Optional<User> optionalUser = userService.getUser(username);

        return optionalUser
                .map(user -> ResponseEntity.ok(UserMapper.INSTANCE.userEntitytoDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserCreated> registerUser(UserDTO userInfo){
        UserDTO createdUser = userService.registerUser(userInfo);

        UserCreated response = new UserCreated();
        response.setMessage("User registered successfully.");
        response.setUserId(createdUser.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
