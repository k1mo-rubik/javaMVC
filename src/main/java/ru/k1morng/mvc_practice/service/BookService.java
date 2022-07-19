package ru.k1morng.mvc_practice.service;

import org.springframework.stereotype.Service;
import ru.k1morng.mvc_practice.entity.Book;
import ru.k1morng.mvc_practice.repository.BookRepository;

import java.util.List;
import java.util.UUID;
@Service
public class BookService {

    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public void postBook(Book book){
        book.setId(UUID.randomUUID());
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
}
