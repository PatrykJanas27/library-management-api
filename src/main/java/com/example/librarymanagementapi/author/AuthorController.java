package com.example.librarymanagementapi.author;

import com.example.librarymanagementapi.author.dto.GetAuthorDto;
import com.example.librarymanagementapi.author.dto.FillAuthorDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AuthorController implements AuthorApi {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //here is a little problem because of relation loop - an author has a List of books and books have the author
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    public GetAuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    public ResponseEntity<GetAuthorDto> saveAuthor(@RequestBody FillAuthorDto fillAuthorDto){
        GetAuthorDto savedAuthor = authorService.saveAuthor(fillAuthorDto);
        URI savedAuthorUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getId())
                .toUri();
        return ResponseEntity.created(savedAuthorUri).body(savedAuthor);
    }

}
