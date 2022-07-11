package com.example.librarymanagementapi.book;

import com.example.librarymanagementapi.author.Author;
import com.example.librarymanagementapi.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime timeAdded;
    @JsonIgnore //to solve the problem for method "public List<Book> getBooks()" of BookController
    @ManyToOne
    @JoinColumn(name = "author_id") //the owner of relation
    private Author author;
    @JsonIgnore //to solve the problem for method "public List<Book> getBooks()" of BookController
    //book can have one category or more for example romance and fantasy
    @ManyToMany
    @JoinTable(name = "category_book") //the owner of relation
    private List<Category> categories = new ArrayList<>();

    public Book() {
    }

    public Book(String title, Author author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.timeAdded = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timeAdded=" + timeAdded +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) && Objects.equals(title, book.title) && Objects.equals(description, book.description) && timeAdded.equals(book.timeAdded) && Objects.equals(author, book.author) && Objects.equals(categories, book.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, timeAdded, author, categories);
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
