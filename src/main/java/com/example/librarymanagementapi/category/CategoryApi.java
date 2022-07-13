package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.category.dto.FillCategoryDto;
import com.example.librarymanagementapi.category.dto.GetCategoryBookDto;
import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetCategoryDto> saveCategory(@Valid @RequestBody FillCategoryDto fillCategoryDto);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategoryById(@PathVariable Long id);
}
