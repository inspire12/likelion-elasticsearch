package com.inspire12.likelionelasticsearch.application.service;

import com.inspire12.likelionelasticsearch.domain.repository.BookRepository;
import com.inspire12.likelionelasticsearch.infrastructure.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
