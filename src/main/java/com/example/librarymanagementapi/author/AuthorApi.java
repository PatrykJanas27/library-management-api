package com.example.librarymanagementapi.author;

import com.example.librarymanagementapi.author.dto.FillAuthorDto;
import com.example.librarymanagementapi.author.dto.GetAuthorBookDto;
import com.example.librarymanagementapi.author.dto.GetAuthorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/authors")
public interface AuthorApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Author> getAuthors();

    @GetMapping("/{id}")
    GetAuthorDto getAuthorById(@PathVariable Long id);

    @GetMapping("/{id}/books")
    ResponseEntity<List<GetAuthorBookDto>> getBooksByAuthorId(@PathVariable Long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetAuthorDto> saveAuthor(@Valid @RequestBody FillAuthorDto fillAuthorDto);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAuthorById(@PathVariable Long id);

}
