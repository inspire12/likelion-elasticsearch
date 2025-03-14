package com.inspire12.likelionelasticsearch.infrastructure.esrepository;

import com.inspire12.likelionelasticsearch.domain.repository.BookRepository;
import com.inspire12.likelionelasticsearch.infrastructure.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookEsAdapter implements BookRepository {

    private final BookEsRepository bookEsRepository;

    public BookEsAdapter(BookEsRepository bookEsRepository) {
        this.bookEsRepository = bookEsRepository;
    }

    @Override
    public Book save(Book book) {
        return bookEsRepository.save(book);
    }

    @Override
    public List<Book> findByTitleContaining(String title) {
        return bookEsRepository.findByTitleContaining(title);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookEsRepository.findAll();
    }
}
