package ru.k1morng.mvc_practice.service;

import org.springframework.stereotype.Service;
import ru.k1morng.mvc_practice.dto.BookDto;
import ru.k1morng.mvc_practice.entity.Book;
import ru.k1morng.mvc_practice.mapper.BookMapper;
import ru.k1morng.mvc_practice.repository.BookRepository;

import java.util.List;
import java.util.UUID;
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
    public List<Book> getBookList(){
        return bookRepository.findAll();
    }
    public List<Book> getBook(String name){
        return bookRepository.findBookByName(name);
    }
    public Book getBookById(UUID id){
        return bookRepository.findById(id).get();
    }
}
