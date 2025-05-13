package com.example.swad8.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.swad8.models.Category;
import com.example.swad8.dtos.CategoryDto;
import com.example.swad8.dtos.CreateCategoryDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto dto);

    Category toEntity(CreateCategoryDto dto);
}
