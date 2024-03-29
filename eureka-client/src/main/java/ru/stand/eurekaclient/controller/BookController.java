package ru.stand.eurekaclient.controller;

import org.springframework.web.bind.annotation.*;
import ru.stand.eurekaclient.dto.BookDto;
import ru.stand.eurekaclient.entity.Book;
import ru.stand.eurekaclient.service.BookService;

import java.util.List;
import java.util.UUID;


//TODO привести всю архитектуру в вид как Author (все классы)
@RestController()
public class BookController {

        private BookService bookService;

        public BookController(BookService bookService) {
            this.bookService = bookService;
        }


        @PostMapping("books")
        public String postBook(@RequestBody BookDto newBook) {
            bookService.postBook(newBook);
            return "success";
        }
        @GetMapping("test")
        public String test() {
            return "123";
        }

        @DeleteMapping("books")
        public String delBook() {
            bookService.delBook();
            return "sucess";
        }


        @DeleteMapping("books/{id}")
        public String delBook(@PathVariable(value = "id") UUID id) {
            bookService.delBook(id);
            return "sucess";
        }


        @GetMapping("books")
        public List<Book> getBook() {
            return bookService.getBookList();
        }




        @GetMapping("books/{name}")
        public List<Book> getBook(@PathVariable(value = "name") String name) {
            return bookService.getBook(name);
        }
    }

