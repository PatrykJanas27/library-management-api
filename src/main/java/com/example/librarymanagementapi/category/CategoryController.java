package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.author.dto.GetAuthorBookDto;
import com.example.librarymanagementapi.category.dto.GetCategoryBookDto;
import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @Override
    public GetCategoryDto getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    @Override
    public ResponseEntity<List<GetCategoryBookDto>> getBooksByCategoryId(Long id) {
        return ResponseEntity.ok(categoryService.getBooksByCategoryId(id));
    }

}
