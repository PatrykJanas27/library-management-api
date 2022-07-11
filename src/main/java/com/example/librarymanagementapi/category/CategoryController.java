package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public GetCategoryDto getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }
}
