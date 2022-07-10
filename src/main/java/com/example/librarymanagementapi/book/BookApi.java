package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.BookDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/books")
public interface BookApi {

    @GetMapping("/{id}")
    Book getBookById(@PathVariable Long id);
    @GetMapping
    List<Book> getAllBooks();
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ExceptionHandler
    ResponseEntity<BookDto> saveBook(@RequestBody @Valid BookDto bookDto);
}
