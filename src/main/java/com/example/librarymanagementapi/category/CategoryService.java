package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.category.dto.CategoryDtoMapper;
import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public GetCategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryDtoMapper::map)
                .orElseThrow(
                        () -> new NotFoundException("Category with id " + id + " is not exists")
                );
    }


    @Transactional
    void save(Category category) {
        categoryRepository.save(category);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new Category("Horror"));
        save(new Category("Fantasy"));
        save(new Category("Romance"));
    }
}
