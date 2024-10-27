package com.ratethisthing.ratethisthing.mappers;

import com.ratethisthing.ratethisthing.entity.User;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO userEntitytoDto(User user);
}


