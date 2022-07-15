package com.example.librarymanagementapi.author;

import com.example.librarymanagementapi.author.dto.FillAuthorDto;
import com.example.librarymanagementapi.author.dto.GetAuthorBookDto;
import com.example.librarymanagementapi.author.dto.GetAuthorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AuthorController implements AuthorApi {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //here is a little problem because of relation loop - an author has a List of books and books have the author
    @Override
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @Override
    public GetAuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @Override
    public ResponseEntity<List<GetAuthorBookDto>> getBooksByAuthorId(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getBooksByAuthorId(id));
    }

    @Override
    public ResponseEntity<GetAuthorDto> saveAuthor(@RequestBody FillAuthorDto fillAuthorDto) {
        GetAuthorDto savedAuthor = authorService.saveAuthor(fillAuthorDto);
        URI savedAuthorUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getId())
                .toUri();
        return ResponseEntity.created(savedAuthorUri).body(savedAuthor);
    }

    @Override
    public ResponseEntity<?> deleteAuthorById(Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> replaceAuthor(@PathVariable Long id, @Valid @RequestBody FillAuthorDto fillAuthorDto){
        return authorService.replaceAuthor(id, fillAuthorDto)
                .map(c -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

}
