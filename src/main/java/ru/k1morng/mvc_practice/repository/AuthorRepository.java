package ru.k1morng.mvc_practice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.k1morng.mvc_practice.dto.AuthorDto;
import ru.k1morng.mvc_practice.entity.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>{

    List<Author> findAuthorsByName(String name);

//    private List<AuthorDto> authorList;
//    public AuthorRepository(){
//        authorList = new ArrayList<>();
//    }
//    public void addAuthor(AuthorDto authorDto){
//        authorList.add(authorDto);
//    }
//    public List<AuthorDto> getAuthorList() {
//        return authorList;
//    }
}
