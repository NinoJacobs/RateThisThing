package com.ratethisthing.ratethisthing.service;

import com.ratethisthing.ratethisthing.entity.Category;
import com.ratethisthing.ratethisthing.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
