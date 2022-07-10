package com.example.librarymanagementapi.author.dto;

import com.example.librarymanagementapi.author.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorDtoMapper {

    public GetAuthorDto map(Author author) {
        GetAuthorDto getAuthorDto = new GetAuthorDto();
        getAuthorDto.setId(author.getId());
        getAuthorDto.setFirstName(author.getFirstName());
        getAuthorDto.setLastName(author.getLastName());
        return getAuthorDto;
    }

    public Author map(FillAuthorDto fillAuthorDto) {
        Author author = new Author();
        author.setFirstName(fillAuthorDto.getFirstName());
        author.setLastName(fillAuthorDto.getLastName());
        return author;
    }
}
