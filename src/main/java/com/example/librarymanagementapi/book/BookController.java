package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.book.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }


    public ResponseEntity<BookDto> saveBook(@RequestBody @Valid BookDto bookDto){
        BookDto savedBook = bookService.saveBook(bookDto);
        URI savedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedBook);
    }
}
