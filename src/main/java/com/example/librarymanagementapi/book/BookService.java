package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.BookDtoMapper;
import com.example.librarymanagementapi.book.dto.FillBookDto;
import com.example.librarymanagementapi.book.dto.GetBookDto;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    public BookService(BookRepository bookRepository, BookDtoMapper bookDtoMapper) {
        this.bookRepository = bookRepository;
        this.bookDtoMapper = bookDtoMapper;
    }

    public List<GetBookDto> getBooks() {
        List<GetBookDto> listOfGetBookDto= new ArrayList<>();
        Iterable<Book> books = bookRepository.findAll();
        for (Book book : books) {
            listOfGetBookDto.add(bookDtoMapper.map(book));
        }
        return listOfGetBookDto;
    }

    public GetBookDto getBookById(Long id) {
        return bookRepository.findById(id).map(bookDtoMapper::map)
                .orElseThrow(
                        () -> new NotFoundException("Book with id " + id + " is not exists")
                );
    }

    public List<GetBookDto> getBooksByTitle(String title) {
        List<GetBookDto> listOfBooksByTitle = bookRepository
                .findAllByTitle(title)
                .stream()
                .map(bookDtoMapper::map)
                .collect(Collectors.toList());
        if (listOfBooksByTitle.isEmpty()) {
            throw new NotFoundException("Book with title '" + title + "' is not exists");
        } else {
            return listOfBooksByTitle;
        }
    }

    @Transactional
    public GetBookDto saveBook(FillBookDto fillBookDto) {
        Book bookToSave = bookDtoMapper.map(fillBookDto);
        bookToSave.setTimeAdded(LocalDateTime.now());
        Book savedBook = bookRepository.save(bookToSave);
        return bookDtoMapper.map(savedBook);
    }

    @Transactional
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)){
            throw new NotFoundException("Book with id " + id + " is not exists");
        }
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
        Book updatedBook = bookRepository.save(bookToUpdate);
        return Optional.of(bookDtoMapper.map(updatedBook));
    }
}
