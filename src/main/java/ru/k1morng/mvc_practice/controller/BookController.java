package ru.k1morng.mvc_practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k1morng.mvc_practice.dto.BookDto;
import ru.k1morng.mvc_practice.entity.Book;
import ru.k1morng.mvc_practice.service.BookService;

import java.util.List;
import java.util.UUID;

@RestController
public class BookController {

        private BookService bookService;

        public BookController(BookService bookService) {
            this.bookService = bookService;
        }


        @PostMapping("postbook")
        public ResponseEntity<String> postBook(@RequestBody BookDto newBook) {
            bookService.postBook(newBook);
            return ResponseEntity.ok("sucess");
        }

        @DeleteMapping("delbook")
        public ResponseEntity<String> delBook() {
            bookService.delBook();
            return ResponseEntity.ok("sucess");
        }


        @DeleteMapping("delbook/{id}")
        public ResponseEntity<String> delBook(@PathVariable(value = "id") UUID id) {
            bookService.delBook(id);
            return ResponseEntity.ok("sucess");
        }


        @GetMapping("getbook")
        public ResponseEntity<List<BookDto>> getBook() {
            return bookService.getBookList();
        }




        @GetMapping("getbook/{name}")
        public ResponseEntity<List<BookDto>> getBook(@PathVariable(value = "name") String name) {
            return bookService.getBook(name);
        }
    }

