package ru.k1morng.mvc_practice.service;


import org.springframework.stereotype.Service;
import ru.k1morng.mvc_practice.dto.AuthorDto;
import ru.k1morng.mvc_practice.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    public void postAuthor(AuthorDto authorDto){
        authorRepository.addAuthor(authorDto);
    }
    public List<AuthorDto> getAuthorList(){
        return authorRepository.getAuthorList();
    }
}
