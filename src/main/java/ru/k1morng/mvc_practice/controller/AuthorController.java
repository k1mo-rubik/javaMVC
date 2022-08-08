package ru.k1morng.mvc_practice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k1morng.mvc_practice.dto.AuthorDto;
import ru.k1morng.mvc_practice.handler.ControllerExceptionHandler;
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
    public ResponseEntity<String> postAuthors(@RequestBody AuthorDto newAuthorDto) {
        authorService.postAuthor(newAuthorDto);
        return ResponseEntity.ok("sucess");
    }
    @PostMapping("authors/add/{id}/book/{book_id}")
    public ResponseEntity<String> addAuthorsBook(@PathVariable(value = "id") UUID id,
                                     @PathVariable(value = "book_id") UUID book_id)
    {

        authorService.postAuthorsBook(id, book_id);
        return ResponseEntity.ok("sucess");
    }
    @DeleteMapping("delauthors")
    public ResponseEntity<String> delAuthors() {
        authorService.delAuthors();
        return ResponseEntity.ok("sucess");
    }


    @DeleteMapping("delauthors/{id}")
    public ResponseEntity<String> delAuthor(@PathVariable(value = "id") UUID id) {
        authorService.delAuthor(id);
        return ResponseEntity.ok("sucess");
    }


    @GetMapping("getauthors/page/{page}/size/{size}")
    @ExceptionHandler(ControllerExceptionHandler.class)
    public ResponseEntity<List<AuthorDto>> getAuthors(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size
            ) {

//    public List<Author> getAuthors() {
        return authorService.getAuthorList(page, size);
//        return authorService.getAuthorList();
    }


    @GetMapping("getauthors/{name}")
    public ResponseEntity<List<AuthorDto>> getAuthor(@PathVariable(value = "name") String name) {
        return authorService.getAuthor(name);
    }
}

