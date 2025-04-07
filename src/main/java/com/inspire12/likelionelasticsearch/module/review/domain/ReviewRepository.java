package com.inspire12.likelionelasticsearch.module.review.domain;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface ReviewRepository {
    @Transactional
    void save(Review review);
}
