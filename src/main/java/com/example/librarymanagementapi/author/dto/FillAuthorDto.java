package com.example.librarymanagementapi.author.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FillAuthorDto {
    @NotBlank(message = "First name may not be empty or null")
    @Size(max = 255, message = "First name must be shorter than 255 signs")
    private String firstName;
    @NotBlank(message = "Last name may not be empty or null")
    @Size(max = 255, message = "Last name must be shorter than 255 signs")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
