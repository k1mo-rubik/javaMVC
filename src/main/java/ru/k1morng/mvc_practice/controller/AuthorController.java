package ru.k1morng.mvc_practice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k1morng.mvc_practice.dto.AuthorDto;
import ru.k1morng.mvc_practice.entity.Author;
import ru.k1morng.mvc_practice.exception.AuthorIsDeletedException;
import ru.k1morng.mvc_practice.exception.EmptyPageException;
import ru.k1morng.mvc_practice.exception.InvalidBookToAuthorException;
import ru.k1morng.mvc_practice.service.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("")
    public ResponseEntity<Author> postAuthor(@Valid @RequestBody AuthorDto newAuthorDto) {
        return new ResponseEntity<>(authorService.postAuthor(newAuthorDto), HttpStatus.CREATED);
    }

    @PostMapping("{id}/book/{book_id}")
    public ResponseEntity<String> addAuthorToBook(@PathVariable(value = "id") UUID id,
                                                  @PathVariable(value = "book_id") UUID book_id) throws InvalidBookToAuthorException
    {
        authorService.postAuthorsBook(id, book_id);
        return ResponseEntity.ok("success");
    }
    @DeleteMapping("")
    public ResponseEntity<String> delAuthors() {
        authorService.delAuthors();
        return ResponseEntity.ok("success");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delAuthor(@PathVariable(value = "id") UUID id) throws AuthorIsDeletedException {
        authorService.delAuthor(id);
        return ResponseEntity.ok("success");
    }


    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<AuthorDto>> getAuthors (
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size
            ) throws EmptyPageException {

        return authorService.getAuthorList(page, size);
    }


    @GetMapping("/{name}")
    public ResponseEntity<List<AuthorDto>> getAuthor(@PathVariable(value = "name") String name) throws AuthorIsDeletedException {
        return authorService.getAuthor(name);
    }
//    @DeleteMapping("/test")
//    public ResponseEntity<List<AuthorDto>> testMethod() throws AuthorIsDeletedException, EmptyPageException{
//
//            AuthorRepository authorRepository = null;
//            UUID.fromString(authorService.delAuthor(getAuthors(0, 1000).getBody().stream().map(AuthorDto::getId).toString())))
//            authorService.delAuthor(getAuthors(0, 1000).getBody().stream().map(AuthorDto::getId).toString());
//
//    }
}

