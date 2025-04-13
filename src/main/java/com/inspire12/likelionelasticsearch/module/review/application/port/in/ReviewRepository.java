package com.inspire12.likelionelasticsearch.module.review.application.port.in;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewRepository {

    Page<Review> getReviewsByCustomerId(Long customerId, Pageable pageable);

    @Transactional
    ReviewDocument save(Review review);

    @Transactional
    void saveBulk(List<ReviewRequest> review);


    // TODO search 의 구현체가 어디에 있는지 확인, SearchHits 에 대해 확인
    Page<Review> search(ReviewSearchRequest request);

    Page<Review> searchByUserInfo(ReviewSearchRequest request);
}
