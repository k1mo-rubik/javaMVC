package ru.k1morng.mvc_practice.service;


import org.springframework.stereotype.Service;
import ru.k1morng.mvc_practice.entity.Author;
import ru.k1morng.mvc_practice.repository.AuthorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    public void postAuthor(Author author){
        author.setId(UUID.randomUUID());
        authorRepository.save(author);
    }
    public void delAuthors(){
        authorRepository.deleteAll();
    }

    public void delAuthor(UUID id){
        authorRepository.deleteById(id);
    }
    public List<Author> getAuthorList(){
        return authorRepository.findAll();
    }
    public List<Author> getAuthor(String name){
        return authorRepository.findAuthorsByName(name);
    }
}
