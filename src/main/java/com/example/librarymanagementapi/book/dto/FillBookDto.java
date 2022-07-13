package com.example.librarymanagementapi.book.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class FillBookDto {

    @NotBlank(message = "Title may not be empty or null") //not null and the trimmed length is greater than zero
    @Size(max = 255, message = "Title must be shorter than {max} signs")
    private String title;
    @Size(max = 255, message = "Description must be shorter than {max} signs")
    private String description;
    @NotNull(message = "Author id may not be null")
    private Long authorId;
    @NotNull(message = "List of category ids may not be null")
    private List<Long> categoryIds;

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
