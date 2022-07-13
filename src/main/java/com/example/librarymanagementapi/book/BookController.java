package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.FillBookDto;
import com.example.librarymanagementapi.book.dto.GetBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class BookController implements BookApi {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @Override
    public GetBookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Override
    public ResponseEntity<GetBookDto> saveBook(@Valid @RequestBody FillBookDto fillBookDto) {
        GetBookDto savedBook = bookService.saveBook(fillBookDto);
        URI savedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedBook);
//        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteBookById(Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
