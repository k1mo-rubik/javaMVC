package ru.k1morng.mvc_practice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k1morng.mvc_practice.dto.AuthorDto;
import ru.k1morng.mvc_practice.entity.Author;
import ru.k1morng.mvc_practice.exception.UserNotFoundException;
import ru.k1morng.mvc_practice.handler.ControllerExceptionHandler;
import ru.k1morng.mvc_practice.service.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // TODO настроить ExceptionHandler
    @PostMapping("author")
    public ResponseEntity<Author> postAuthor(@Valid @RequestBody AuthorDto newAuthorDto) {
        return new ResponseEntity<>(authorService.postAuthor(newAuthorDto), HttpStatus.CREATED);
    }

    @PostMapping("authors/add/{id}/book/{book_id}")
    public ResponseEntity<String> addAuthorToBook(@PathVariable(value = "id") UUID id,
                                                  @PathVariable(value = "book_id") UUID book_id)
    {

        authorService.postAuthorsBook(id, book_id);
        return ResponseEntity.ok("success");
    }
    @DeleteMapping("authors")
    public ResponseEntity<String> delAuthors() {
        authorService.delAuthors();
        return ResponseEntity.ok("success");
    }


    @DeleteMapping("authors/{id}")
    public ResponseEntity<String> delAuthor(@PathVariable(value = "id") UUID id) {
        authorService.delAuthor(id);
        return ResponseEntity.ok("success");
    }


    @GetMapping("authors/page/{page}/size/{size}")
    @ExceptionHandler(ControllerExceptionHandler.class)
    public ResponseEntity<List<AuthorDto>> getAuthors(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size
            ) {

//    public List<Author> getAuthors() {
        return authorService.getAuthorList(page, size);
//        return authorService.getAuthorList();
    }


    @GetMapping("authors/{name}")
    public ResponseEntity<List<AuthorDto>> getAuthor(@PathVariable(value = "name") String name) throws UserNotFoundException {
        return authorService.getAuthor(name);
    }
}

