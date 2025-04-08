package com.inspire12.likelionelasticsearch.module.review.domain;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewRepository {

    List<Review> getReviewsByCustomerId(Long customerId, Pageable pageable);

    @Transactional
    void save(Review review);

    SearchHits<ReviewDocument> search(ReviewSearchRequest request);

}
