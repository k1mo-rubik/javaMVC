package ru.k1morng.mvc_practice.repository;


import org.springframework.stereotype.Repository;
import ru.k1morng.mvc_practice.dto.AuthorDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private List<AuthorDto> authorList;
    public AuthorRepository(){
        authorList = new ArrayList<>();
    }
    public void addAuthor(AuthorDto authorDto){
        authorList.add(authorDto);
    }
    public List<AuthorDto> getAuthorList() {
        return authorList;
    }
}
