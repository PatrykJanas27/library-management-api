package com.example.librarymanagementapi.author.dto;

import com.example.librarymanagementapi.author.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorDtoMapper {

    public GetAuthorDto map(Author author) {
        GetAuthorDto dto = new GetAuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        return dto;
    }

    public Author map(FillAuthorDto fillAuthorDto) {
        Author author = new Author();
        author.setFirstName(fillAuthorDto.getFirstName());
        author.setLastName(fillAuthorDto.getLastName());
        return author;
    }
}
