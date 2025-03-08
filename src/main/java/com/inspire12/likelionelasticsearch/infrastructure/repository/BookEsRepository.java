package com.inspire12.likelionelasticsearch.infrastructure.repository;

import com.inspire12.likelionelasticsearch.infrastructure.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookEsRepository extends ElasticsearchRepository<Book, String> {
    List<Book> findByTitleContaining(String title);
}
