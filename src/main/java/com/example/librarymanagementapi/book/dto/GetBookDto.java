package com.example.librarymanagementapi.book.dto;

import java.time.LocalDateTime;

public class GetBookDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime timeAdded;
    private Long authorId;
    private String authorFirstName;
    private String authorLastName;

    public LocalDateTime getLocalDateTime() {
        return timeAdded;
    }

    public void setLocalDateTime(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
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


}
