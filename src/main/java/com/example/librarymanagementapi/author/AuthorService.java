package com.example.librarymanagementapi.author;

import com.example.librarymanagementapi.author.dto.AuthorDtoMapper;
import com.example.librarymanagementapi.author.dto.FillAuthorDto;
import com.example.librarymanagementapi.author.dto.GetAuthorBookDto;
import com.example.librarymanagementapi.author.dto.GetAuthorDto;
import com.example.librarymanagementapi.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorDtoMapper authorDtoMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorDtoMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorDtoMapper = authorMapper;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public GetAuthorDto getAuthorById(Long id) {
        return authorRepository.findById(id).map(authorDtoMapper::map)
                .orElseThrow(
                        () -> new NotFoundException("Author with id " + id + " is not exists")
                );
    }

    public List<GetAuthorBookDto> getBooksByAuthorId(Long authorId) {
        return authorRepository.findById(authorId)
                .map(Author::getBooks)
//                .orElse(Collections.emptyList())
                .orElseThrow(() -> new NotFoundException("Author with id " + authorId + " is not exists"))
                .stream()
                .map(authorDtoMapper::mapAuthorToAuthorBookDto)
                .toList();
    }

    @Transactional
    GetAuthorDto saveAuthor(FillAuthorDto fillAuthorDto) {
        Author authorToSave = authorDtoMapper.map(fillAuthorDto);
        Author savedAuthor = authorRepository.save(authorToSave);
        return authorDtoMapper.map(savedAuthor); //Author to AuthorDto (to showing/getting data)
    }

    @Transactional
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
