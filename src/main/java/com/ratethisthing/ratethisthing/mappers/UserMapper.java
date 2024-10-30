package com.ratethisthing.ratethisthing.mappers;

import com.ratethisthing.ratethisthing.entity.User;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO userEntityToDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User userDtoToEntity(UserDTO userDto);
}