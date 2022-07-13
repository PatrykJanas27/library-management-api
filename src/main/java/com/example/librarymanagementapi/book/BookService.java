package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.BookDtoMapper;
import com.example.librarymanagementapi.book.dto.FillBookDto;
import com.example.librarymanagementapi.book.dto.GetBookDto;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return bookDtoMapper.map(savedBook);
    }

    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public Optional<?> replaceBook(Long id, FillBookDto fillBookDto) {
        if (!bookRepository.existsById(id)) {
            return Optional.empty();
        }
        Book bookToUpdate = bookDtoMapper.map(fillBookDto);
        bookToUpdate.setId(id);
        bookToUpdate.setTimeAdded(LocalDateTime.now());
        Book updatedEntity = bookRepository.save(bookToUpdate);
        return Optional.of(bookDtoMapper.map(updatedEntity));
    }
}
