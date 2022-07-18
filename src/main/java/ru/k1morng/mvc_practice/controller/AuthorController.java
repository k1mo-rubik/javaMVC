package ru.k1morng.mvc_practice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.k1morng.mvc_practice.dto.AuthorDto;
import ru.k1morng.mvc_practice.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {
    private AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }



@PostMapping("postauthors")
    public String postAuthors(@RequestBody AuthorDto newAuthorDto){
        authorService.postAuthor(newAuthorDto);
        return "sucess";
    }

    @GetMapping("getauthors")
    public List<AuthorDto> getAuthors(){
        return authorService.getAuthorList();
    }
}

