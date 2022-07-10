package com.example.librarymanagementapi.book.dto;

import com.example.librarymanagementapi.author.AuthorRepository;
import com.example.librarymanagementapi.book.Book;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookDtoMapper {
    private final AuthorRepository authorRepository;

    public BookDtoMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    //from entity to DTO
    public BookDto map(Book book){
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setLocalDateTime(book.getTimeAdded());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setAuthorFirstName(book.getAuthor().getFirstName()); //splaszczenie zagniezdzenia do wyswietlania
        dto.setAuthorLastName(book.getAuthor().getLastName());
        return dto;
    }

    //from DTO to entity
    public Book map(BookDto dto){
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        authorRepository.findById(dto.getAuthorId())
                .ifPresentOrElse(book::setAuthor,
                        () ->{ throw new NotFoundException("Author with id " + dto.getAuthorId() + " is not exists");}
                ); //jesli taki autor z takim id istnieje to przypisz tego autora do ksiazki
        return book;
    }

}
