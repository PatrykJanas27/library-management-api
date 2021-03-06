package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.FillBookDto;
import com.example.librarymanagementapi.book.dto.GetBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/books")
public interface BookApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<GetBookDto> getBooks();

    @GetMapping("/{id}")
    GetBookDto getBookById(@PathVariable Long id);

    @GetMapping("/title")
    List<GetBookDto> getBooksByTitle(@RequestParam String title);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetBookDto> saveBook(@RequestBody @Valid FillBookDto fillBookDto);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBookById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<?> replaceBook(@PathVariable Long id, @Valid @RequestBody FillBookDto fillBookDto);
}
