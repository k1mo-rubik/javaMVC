package ru.stand.eurekaclient.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stand.eurekaclient.entity.Author;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>{

    Page<Author> findAuthorsByDeletedIsFalse(Pageable pageable);
    List<Author> findAuthorsByDeletedIsFalseAndName(String name);


}
