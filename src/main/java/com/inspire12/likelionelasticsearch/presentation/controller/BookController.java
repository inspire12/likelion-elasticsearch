package com.inspire12.likelionelasticsearch.presentation.controller;

import com.inspire12.likelionelasticsearch.application.service.BookService;
import com.inspire12.likelionelasticsearch.infrastructure.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/search")
    public List<Book> searchBooks(@RequestBody Book book) {
        return bookService.searchBooks(book.getTitle());
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
