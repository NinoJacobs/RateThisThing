package com.ratethisthing.ratethisthing.controller;

import com.ratethisthing.ratethisthing.entity.Category;
import com.ratethisthing.ratethisthing.mappers.CategoryMapper;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.api.CategoryApi;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.CategoryDTO;
import com.ratethisthing.ratethisthing.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<Category> categoryEntities = categoryService.findAll();

        if (categoryEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CategoryDTO> categoryList = categoryEntities.stream()
                .map(CategoryMapper.INSTANCE::categoryEntityToDto)
                .toList();

        return ResponseEntity.ok(categoryList);
    }
}
