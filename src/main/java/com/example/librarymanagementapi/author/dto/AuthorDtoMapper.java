package com.example.librarymanagementapi.author.dto;

import com.example.librarymanagementapi.author.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorDtoMapper {
    public Author map(AuthorDto dto){
        Author author = new Author();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        return  author;
    }

    public AuthorDto map(Author author){
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        return dto;
    }
}
