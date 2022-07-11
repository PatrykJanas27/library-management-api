package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.author.dto.GetAuthorBookDto;
import com.example.librarymanagementapi.category.dto.GetCategoryBookDto;
import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/categories")
public interface CategoryApi {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<Category> getCategories();

    @GetMapping("/{id}")
    GetCategoryDto getCategoryById(@PathVariable Long id);

    @GetMapping("/{id}/books")
    ResponseEntity<List<GetCategoryBookDto>> getBooksByCategoryId(@PathVariable Long id);

}
