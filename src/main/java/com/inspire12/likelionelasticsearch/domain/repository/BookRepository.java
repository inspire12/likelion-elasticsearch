package com.inspire12.likelionelasticsearch.domain.repository;

import com.inspire12.likelionelasticsearch.infrastructure.entity.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findByTitleContaining(String title);

    Iterable<Book> findAll();
}
