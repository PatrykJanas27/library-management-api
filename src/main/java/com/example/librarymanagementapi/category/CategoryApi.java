package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/categories")
public interface CategoryApi {

    @GetMapping("/{id}")
    GetCategoryDto getCategoryById(@PathVariable Long id);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<Category> getCategories();
}
