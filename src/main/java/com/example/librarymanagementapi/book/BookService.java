package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.BookDto;
import com.example.librarymanagementapi.book.dto.BookDtoMapper;
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

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with id " + id + " is not exists")
        );
    }

    @Transactional
    BookDto saveBook(BookDto dto) {
        Book bookToSave = bookDtoMapper.map(dto);
        bookToSave.setTimeAdded(LocalDateTime.now()); //tutaj ustawiamy date tworzonego obiektu
        Book savedBook = bookRepository.save(bookToSave); //save
        return bookDtoMapper.map(savedBook);
    }
}
