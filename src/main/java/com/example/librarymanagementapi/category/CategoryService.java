package com.example.librarymanagementapi.category;

import com.example.librarymanagementapi.category.dto.CategoryDtoMapper;
import com.example.librarymanagementapi.category.dto.FillCategoryDto;
import com.example.librarymanagementapi.category.dto.GetCategoryBookDto;
import com.example.librarymanagementapi.category.dto.GetCategoryDto;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public List<GetCategoryDto> getCategories() {
        List<GetCategoryDto> listOfGetCategoryDto = new ArrayList<>();
        Iterable<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            listOfGetCategoryDto.add(categoryDtoMapper.map(category));
        }
        return listOfGetCategoryDto;
    }

    public GetCategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryDtoMapper::map)
                .orElseThrow(
                        () -> new NotFoundException("Category with id " + id + " is not exists")
                );
    }

    public List<GetCategoryBookDto> getBooksByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId).map(Category::getBooks)
                .orElseThrow(() -> new NotFoundException("Category with id " + categoryId + " is not exists"))
                .stream()
                .map(categoryDtoMapper::mapCategoryToCategoryBookDto)
                .toList();
    }

    @Transactional
    public GetCategoryDto saveCategory(FillCategoryDto fillCategoryDto) {
        Category categoryToSave = categoryDtoMapper.map(fillCategoryDto);
        Category savedCategory = categoryRepository.save(categoryToSave);
        return categoryDtoMapper.map(savedCategory);
    }

    @Transactional
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)){
            throw new NotFoundException("Category with id " + id + " is not exists");
        }
        categoryRepository.deleteById(id);
    }

    @Transactional
    public Optional<?> replaceCategory(Long id, FillCategoryDto fillCategoryDto) {
        if (!categoryRepository.existsById(id)) {
            return Optional.empty();
        }
        Category categoryToUpdate = categoryDtoMapper.map(fillCategoryDto);
        categoryToUpdate.setId(id);
        Category updatedCategory = categoryRepository.save(categoryToUpdate);
        return Optional.of(categoryDtoMapper.map(updatedCategory));
    }
}
