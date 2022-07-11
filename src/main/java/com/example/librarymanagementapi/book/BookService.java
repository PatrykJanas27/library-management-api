package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.BookDtoMapper;
import com.example.librarymanagementapi.book.dto.FillBookDto;
import com.example.librarymanagementapi.book.dto.GetBookDto;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    public BookService(BookRepository bookRepository, BookDtoMapper bookDtoMapper) {
        this.bookRepository = bookRepository;
        this.bookDtoMapper = bookDtoMapper;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public GetBookDto getBookById(Long id) {
        return bookRepository.findById(id).map(bookDtoMapper::map)
                .orElseThrow(
                        () -> new NotFoundException("Book with id " + id + " is not exists")
                );
    }

    @Transactional
    GetBookDto saveBook(FillBookDto fillBookDto) {
        Book bookToSave = bookDtoMapper.map(fillBookDto);
        bookToSave.setTimeAdded(LocalDateTime.now());
        Book savedBook = bookRepository.save(bookToSave);
        return bookDtoMapper.map(savedBook); //Book to GetBookDto
    }
}
