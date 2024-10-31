package com.ratethisthing.ratethisthing.mappers;

import com.ratethisthing.ratethisthing.entity.Item;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(target = "categoryName", source = "category.name")
    ItemDTO itemEntityToDto(Item user);
}