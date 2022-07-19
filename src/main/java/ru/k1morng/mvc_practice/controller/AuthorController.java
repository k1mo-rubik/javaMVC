package ru.k1morng.mvc_practice.controller;


import org.springframework.web.bind.annotation.*;
import ru.k1morng.mvc_practice.entity.Author;
import ru.k1morng.mvc_practice.entity.Book;
import ru.k1morng.mvc_practice.service.AuthorService;

import java.util.List;
import java.util.UUID;

@RestController
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping("postauthors")
    public String postAuthors(@RequestBody Author newAuthor) {
        authorService.postAuthor(newAuthor);
        return "sucess";
    }
    @PostMapping("authors/add/{id}/book/{book_id}")
    public String addAuthorsBook(@PathVariable(value = "id") UUID id,
                                     @PathVariable(value = "book_id") UUID book_id)
    {

        authorService.postAuthorsBook(id, book_id);
        return "sucess";
    }
    @DeleteMapping("delauthors")
    public String delAuthors() {
        authorService.delAuthors();
        return "sucess";
    }


    @DeleteMapping("delauthors/{id}")
    public String delAuthor(@PathVariable(value = "id") UUID id) {
        authorService.delAuthor(id);
        return "sucess";
    }


    @GetMapping("getauthors")
    public List<Author> getAuthors() {
        return authorService.getAuthorList();
    }


    @GetMapping("getauthors/{name}")
    public List<Author> getAuthor(@PathVariable(value = "name") String name) {
        return authorService.getAuthor(name);
    }
}

