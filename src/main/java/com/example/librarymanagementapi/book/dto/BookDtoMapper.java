package com.example.librarymanagementapi.book.dto;

import com.example.librarymanagementapi.author.AuthorRepository;
import com.example.librarymanagementapi.book.Book;
import com.example.librarymanagementapi.category.CategoryRepository;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookDtoMapper {
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookDtoMapper(AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public GetBookDto map(Book book) {
        GetBookDto getBookDto = new GetBookDto();
        getBookDto.setId(book.getId());
        getBookDto.setTitle(book.getTitle());
        getBookDto.setDescription(book.getDescription());
        getBookDto.setTimeAdded(book.getTimeAdded());
        getBookDto.setAuthorId(book.getAuthor().getId());
        getBookDto.setAuthorFirstName(book.getAuthor().getFirstName());
        getBookDto.setAuthorLastName(book.getAuthor().getLastName());
        getBookDto.setCategories(book.getCategories());
        return getBookDto;
    }

    public Book map(FillBookDto fillBookDto) {
        Book book = new Book();
        book.setTitle(fillBookDto.getTitle());
        book.setDescription(fillBookDto.getDescription());
        //if author with such id exist then save the book, otherwise throw an exception
        authorRepository.findById(fillBookDto.getAuthorId())
                .ifPresentOrElse(book::setAuthor,
                        () -> {
                            throw new NotFoundException("Author with id " + fillBookDto.getAuthorId() + " is not exists");
                        }
                );
        for (Long categoryId : fillBookDto.getCategoryIds()) {
            if(categoryId==null){
                throw new NotFoundException("Category can not be null");
            }
            categoryRepository.findById(categoryId)
                    .ifPresentOrElse(book::addCategoryToCategoryIds,
                    () -> {
                        throw new NotFoundException("Category with id " + categoryId + " is not exists");
                    }
                );
        }
        return book;
    }

}
