package com.example.librarymanagementapi.category.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FillCategoryDto {
    @NotBlank(message = "Name may not be empty or null")
    @Size(max = 255, message = "Name must be shorter than 255 signs")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
