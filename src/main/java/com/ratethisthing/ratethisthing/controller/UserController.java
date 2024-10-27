package com.ratethisthing.ratethisthing.controller;

import com.ratethisthing.ratethisthing.entity.User;
import com.ratethisthing.ratethisthing.mappers.UserMapper;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.api.UserApi;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.UserDTO;
import com.ratethisthing.ratethisthing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        return ResponseEntity.ok(userList); // Return 200 OK with the list of users
    }

}
