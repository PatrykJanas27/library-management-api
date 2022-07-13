package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.category.dto.FillCategoryDto;
import com.example.librarymanagementapi.category.dto.GetCategoryBookDto;
import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @Override
    public ResponseEntity<GetCategoryDto> saveCategory(FillCategoryDto fillCategoryDto) {
        GetCategoryDto savedCategory = categoryService.saveCategory(fillCategoryDto);
        URI savedCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
        return ResponseEntity.created(savedCategoryUri).body(savedCategory);
    }

    @Override
    public ResponseEntity<?> deleteCategoryById(Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}
