package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.FillBookDto;
import com.example.librarymanagementapi.book.dto.GetBookDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/books")
public interface BookApi {

    @GetMapping("/{id}")
    GetBookDto getBookById(@PathVariable Long id);

    @GetMapping
    List<Book> getBooks();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ExceptionHandler
    ResponseEntity<GetBookDto> saveBook(@RequestBody @Valid FillBookDto fillBookDto);
}
