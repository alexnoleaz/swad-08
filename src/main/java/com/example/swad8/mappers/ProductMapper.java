package com.example.swad8.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.example.swad8.models.Product;
import com.example.swad8.dtos.ProductDto;
import com.example.swad8.dtos.CreateProductDto;
import com.example.swad8.dtos.UpdateProductDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(CreateProductDto dto);

    void updateProductFromDto(UpdateProductDto dto, @MappingTarget Product product);
}