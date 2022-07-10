package com.example.librarymanagementapi.author;

import com.example.librarymanagementapi.author.dto.FillAuthorDto;
import com.example.librarymanagementapi.author.dto.GetAuthorDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/authors")
public interface AuthorApi {

    @GetMapping
    List<Author> getAllAuthors();

    @GetMapping("/{id}")
    GetAuthorDto getAuthorById(@PathVariable Long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetAuthorDto> saveAuthor(@RequestBody FillAuthorDto fillAuthorDto);

}
