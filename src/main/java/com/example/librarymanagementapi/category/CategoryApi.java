package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/category")
public interface CategoryApi {

    @GetMapping("/{id}")
    GetCategoryDto getCategoryById(@PathVariable Long id);
}
