package com.example.swad8.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swad8.dtos.CategoryDto;
import com.example.swad8.dtos.CreateCategoryDto;
import com.example.swad8.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Categories")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Find all categories")
    @ApiResponse(responseCode = "200", description = "Successfully found all categories")
    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Operation(summary = "Find a category by ID")
    @ApiResponse(responseCode = "200", description = "Successfully found a category")
    @GetMapping("/{id}")
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

    @Operation(summary = "Create a new category")
    @ApiResponse(responseCode = "201", description = "Successfully created a new category")
    @PostMapping
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CreateCategoryDto input) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.create(input));
    }

    @Operation(summary = "Delete a category by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted a category")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
