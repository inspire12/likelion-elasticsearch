package com.inspire12.likelionelasticsearch.module.review.domain;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewRepository {

    List<Review> getReviewsByCustomerId(Long customerId, Pageable pageable);

    @Transactional
    void save(Review review);
}
