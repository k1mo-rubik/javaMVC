package ru.stand.eurekaclient.service;

import org.springframework.stereotype.Service;
import ru.stand.eurekaclient.dto.BookDto;
import ru.stand.eurekaclient.entity.Book;
import ru.stand.eurekaclient.mapper.BookMapper;
import ru.stand.eurekaclient.repository.BookRepository;

import java.util.List;
import java.util.UUID;
@Service
public class BookService {

    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public void postBook(BookDto bookdto){
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
