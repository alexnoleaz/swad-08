package com.example.swad8.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.swad8.dtos.CategoryDto;
import com.example.swad8.dtos.CreateCategoryDto;
import com.example.swad8.mappers.CategoryMapper;
import com.example.swad8.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoryDto findById(Long id) {
        return categoryMapper.toDto(
                categoryRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found")));

    }

    public CategoryDto create(CreateCategoryDto input) {
        var category = categoryMapper.toEntity(input);
        var dbCategory = categoryRepository.save(category);

        return categoryMapper.toDto(dbCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
