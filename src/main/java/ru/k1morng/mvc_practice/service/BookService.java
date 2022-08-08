package ru.k1morng.mvc_practice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.k1morng.mvc_practice.dto.BookDto;
import ru.k1morng.mvc_practice.entity.Book;
import ru.k1morng.mvc_practice.mapper.AuthorMapper;
import ru.k1morng.mvc_practice.mapper.BookMapper;
import ru.k1morng.mvc_practice.repository.BookRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public void postBook(BookDto bookdto){
        //Author author = AuthorMapper.INSTANCE.fromDto(authordto);
        Book book = BookMapper.INSTANCE.fromDto(bookdto);
//        book.setId(UUID.randomUUID());
        bookRepository.save(book);
    }
    public void delBook(){
        bookRepository.deleteAll();
    }

    public void delBook(UUID id){
        bookRepository.deleteById(id);
    }
    public ResponseEntity<List<BookDto>> getBookList(){
        return ResponseEntity.ok(bookRepository.findAll().stream().map(BookMapper.INSTANCE::toBookDto).collect(Collectors.toList()));
    }
    public ResponseEntity<List<BookDto>> getBook(String name){
        return  ResponseEntity.ok(bookRepository.findBookByName(name).stream().map(BookMapper.INSTANCE::toBookDto).collect(Collectors.toList()));
    }
    public ResponseEntity<BookDto> getBookById(UUID id){
        return ResponseEntity.ok(BookMapper.INSTANCE.toBookDto(bookRepository.findById(id).get()));
    }

    
}
