package com.example.swad8.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.swad8.dtos.CreateProductDto;
import com.example.swad8.dtos.ProductDto;
import com.example.swad8.dtos.UpdateProductDto;
import com.example.swad8.mappers.ProductMapper;
import com.example.swad8.models.Product;
import com.example.swad8.repositories.CategoryRepository;
import com.example.swad8.repositories.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {
        return productMapper.toDto(
                productRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found")));
    }

    public ProductDto create(CreateProductDto input) {
        var product = productMapper.toEntity(input);
        var dbCategory = categoryRepository.findById(input.getCategoryId()).orElseThrow(
                () -> new NoSuchElementException("Category with id " + input.getCategoryId() + " not found"));

        product.setCategory(dbCategory);
        entityManager.persist(productMapper.toEntity(input));

        return productMapper.toDto(product);
    }

    public ProductDto update(UpdateProductDto input) {
        var dbProduct = productRepository.findById(input.getId())
                .orElseThrow(() -> new NoSuchElementException("Product with id " + input.getId() + " not found"));

        productMapper.updateProductFromDto(input, dbProduct);
        entityManager.persist(dbProduct);

        return productMapper.toDto(dbProduct);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
