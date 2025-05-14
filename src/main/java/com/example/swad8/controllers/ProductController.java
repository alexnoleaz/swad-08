package com.example.swad8.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.swad8.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.swad8.dtos.CreateProductDto;
import com.example.swad8.dtos.ProductDto;
import com.example.swad8.dtos.UpdateProductDto;

@Tag(name = "Products")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Find all products")
    @ApiResponse(responseCode = "200", description = "Successfully found all products")
    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @Operation(summary = "Find a product by ID")
    @ApiResponse(responseCode = "200", description = "Successfully found a product")
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "201", description = "Successfully created a new product")
    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody CreateProductDto input) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(input));
    }

    @Operation(summary = "Update a product by ID")
    @ApiResponse(responseCode = "200", description = "Successfully updated a product")
    @PutMapping("/{id}")
    public ProductDto update(@PathVariable("id") Long id, @Valid @RequestBody UpdateProductDto input) {
        input.setId(id);
        return productService.update(input);
    }

    @Operation(summary = "Delete a product by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
