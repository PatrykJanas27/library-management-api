package com.example.librarymanagementapi.category.dto;

import com.example.librarymanagementapi.category.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryDtoMapper {

    public Category map(FillCategoryDto fillCategoryDto) {
        Category category = new Category();
        category.setName(fillCategoryDto.getName());
        return category;
    }

    public GetCategoryDto map(Category category) {
        GetCategoryDto getCategoryDto = new GetCategoryDto();
        getCategoryDto.setId(category.getId());
        getCategoryDto.setName(category.getName());
        return getCategoryDto;
    }
}
