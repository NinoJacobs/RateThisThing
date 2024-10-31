package com.ratethisthing.ratethisthing.mappers;

import com.ratethisthing.ratethisthing.entity.Category;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryEntityToDto(Category category);

//    Category categoryDtoToEntity(CategoryDTO categoryDTO);
}


