package ru.stand.eurekaclient.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.stand.eurekaclient.dto.AuthorDto;
import ru.stand.eurekaclient.entity.Author;
import ru.stand.eurekaclient.exception.AuthorIsDeletedException;
import ru.stand.eurekaclient.exception.EmptyPageException;
import ru.stand.eurekaclient.exception.InvalidBookToAuthorException;
import ru.stand.eurekaclient.mapper.AuthorMapper;
import ru.stand.eurekaclient.repository.AuthorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private BookService bookService;

    public AuthorService(AuthorRepository authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }


    public Author postAuthor(AuthorDto authordto) {
        Author author = AuthorMapper.INSTANCE.fromDto(authordto);
        author.setCreatedDate(LocalDateTime.now());
        return authorRepository.save(author);
//        return author;
    }

    // TODO решить, нужно ли переносить логику выброса исключений в BookService
    //TODO также NoAuthorFoundException, переопределить выброс сообщений
    public void postAuthorsBook(UUID id, UUID book_id) throws InvalidBookToAuthorException {
        Author author = authorRepository.findById(id).get();
        if(author.isDeleted()){
            throw new InvalidBookToAuthorException(String.format("Author with id %s is deleted", id));
        }
        //TODO поменять на проверку по isDeleted (обработать несуществующий UUID)
        else if(bookService.getBookById(book_id).equals(null)){
            throw new InvalidBookToAuthorException(String.format("No book with id %s found", book_id));
        }
        else{
            author.addAuthorsBook(bookService.getBookById(book_id));
        }
        authorRepository.save(author);
    }

    //TODO пометить всех авторов как удаленных и переместить в другую коллекцию, реализовать логику роллбэка авторов
    public void delAuthors() {
        authorRepository.deleteAll();
    }


    //TODO решить проблему неверно введенного ID (No value present) и Invalid UUID String,
    // выбросить свои исключения (можно отнаследовать от существующих NoAuthorFoundException уже создан)
    // см. todo выше
    public void delAuthor(UUID id) throws AuthorIsDeletedException {
        var deletedAuthor = authorRepository.findById(id).get();
        if(deletedAuthor.isDeleted()){
            throw new AuthorIsDeletedException("User already deleted");
        }
        else {
            deletedAuthor.setDeleted(true);
            authorRepository.save(deletedAuthor);
        }
    }


    public ResponseEntity<List<AuthorDto>> getAuthorList(int page, int size) throws EmptyPageException {
        Pageable pageable = PageRequest.of(page, size);

        if(authorRepository.findAuthorsByDeletedIsFalse(pageable).getContent().isEmpty()){
            throw new EmptyPageException("No authors on this page");
        }
        else{
            return ResponseEntity.ok(authorRepository.findAuthorsByDeletedIsFalse(pageable).
                    stream().map(AuthorMapper.INSTANCE::toAuthorDto).
                    collect(Collectors.toList()));
        }
    }

    //TODO попробовать сделать совет по имени пользователя
    // (ввели "Ale", программа предложит: "Возможно вы имели ввиду Alex?", т.е. поиск по базе внутренней)

    public ResponseEntity<List<AuthorDto>> getAuthor(String name) throws AuthorIsDeletedException {
        if(authorRepository.findAuthorsByDeletedIsFalseAndName(name).size() != 0){
            return ResponseEntity.ok(authorRepository.findAuthorsByDeletedIsFalseAndName(name).
                    stream().map(AuthorMapper.INSTANCE::toAuthorDto).
                    collect(Collectors.toList()));
        }
        else{
            throw new AuthorIsDeletedException(String.format("User with name %s not found", name));
        }


    }
}
