package com.example.librarymanagementapi.category.dto;

import com.example.librarymanagementapi.author.dto.GetAuthorBookDto;
import com.example.librarymanagementapi.book.Book;
import com.example.librarymanagementapi.category.Category;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public GetCategoryBookDto mapCategoryToCategoryBookDto(Book book){
        GetCategoryBookDto dto = new GetCategoryBookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setTimeAdded(book.getTimeAdded());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setAuthorFirstName(book.getAuthor().getFirstName());
        dto.setAuthorLastName(book.getAuthor().getLastName());
        return dto;
    }
}
