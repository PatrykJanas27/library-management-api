package com.example.librarymanagementapi;

import com.example.librarymanagementapi.author.Author;
import com.example.librarymanagementapi.author.AuthorRepository;
import com.example.librarymanagementapi.book.Book;
import com.example.librarymanagementapi.book.BookRepository;
import com.example.librarymanagementapi.category.Category;
import com.example.librarymanagementapi.category.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class InitialValuesSeed {

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public InitialValuesSeed(AuthorRepository authorRepository,
                             CategoryRepository categoryRepository,
                             BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    void save(Author author) {
        authorRepository.save(author);
    }

    void save(Category category) {
        categoryRepository.save(category);
    }

    void save(Book book) {
        bookRepository.save(book);
    }

    public void fillAuthorTableInDatabase() {
        if (authorRepository.findAll().isEmpty()) {
            save(new Author("Stephen", "King"));
            save(new Author("Colleen", "Hoover"));
            save(new Author("George", "R.R. Martin"));
            save(new Author("Stieg", "Larsson"));
            save(new Author("Sarah", "J. Maas"));
            save(new Author("Trudi", "Canavan"));
            save(new Author("Jennifer", "L. Armentrout"));
            save(new Author("Andrzej", "Sapkowski"));
            save(new Author("Harlan", "Coben"));
        }//9 authors
    }

    public void fillCategoryTableInDatabase() {
        if (categoryRepository.findAll().isEmpty()) {
            save(new Category("Horror"));
            save(new Category("Fantasy"));
            save(new Category("Romance"));
            save(new Category("Science-fiction"));
            save(new Category("Self-help book"));
            save(new Category("Novel"));
            save(new Category("Short story"));
            save(new Category("Poem"));
            save(new Category("Essay"));
            save(new Category("Play"));
            save(new Category("Thriller"));
            save(new Category("Detective story"));
            save(new Category("Crime Novel"));
            save(new Category("Biography"));
            save(new Category("Manga"));
            save(new Category("Dictionary"));
            save(new Category("Textbook"));
            save(new Category("Encyclopaedia"));
        }//16 categories
    }

}
