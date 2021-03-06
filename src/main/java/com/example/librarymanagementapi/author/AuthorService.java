package com.example.librarymanagementapi.author;

import com.example.librarymanagementapi.author.dto.AuthorDtoMapper;
import com.example.librarymanagementapi.author.dto.FillAuthorDto;
import com.example.librarymanagementapi.author.dto.GetAuthorBookDto;
import com.example.librarymanagementapi.author.dto.GetAuthorDto;
import com.example.librarymanagementapi.exception.NotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorDtoMapper authorDtoMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorDtoMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorDtoMapper = authorMapper;
    }

    public List<GetAuthorDto> getAuthors() {
        List<GetAuthorDto> listOfGetAuthorDto = new ArrayList<>();
        for (Author author : authorRepository.findAll()) {
            listOfGetAuthorDto.add(authorDtoMapper.map(author));
        }
        return listOfGetAuthorDto;
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
    public GetAuthorDto saveAuthor(FillAuthorDto fillAuthorDto) {
        Author authorToSave = authorDtoMapper.map(fillAuthorDto);
        Author savedAuthor = authorRepository.save(authorToSave);
        return authorDtoMapper.map(savedAuthor); //Author to AuthorDto (to showing/getting data)
    }

    @Transactional
    public void deleteAuthorById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author with id " + id + " is not exists");
        }
        authorRepository.deleteById(id);
    }

    @Transactional
    public Optional<?> replaceAuthor(Long id, FillAuthorDto fillAuthorDto) {
        if (!authorRepository.existsById(id)) {
            return Optional.empty();
        }
        Author authorToUpdate = authorDtoMapper.map(fillAuthorDto);
        authorToUpdate.setId(id);
        Author updatedAuthor = authorRepository.save(authorToUpdate);
        return Optional.of(authorDtoMapper.map(updatedAuthor));
    }
}
