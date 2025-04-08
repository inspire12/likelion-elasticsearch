package com.inspire12.likelionelasticsearch.module.review.application.service;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.ReviewResponse;
import com.inspire12.likelionelasticsearch.module.review.application.port.out.OrderCheckPort;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.domain.ReviewRepository;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import com.inspire12.likelionelasticsearch.module.review.support.ReviewMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderCheckPort orderCheckPort;

    public ReviewService(ReviewRepository reviewRepository, OrderCheckPort orderCheckPort) {
        this.reviewRepository = reviewRepository;
        this.orderCheckPort = orderCheckPort;
    }

    public void saveReview(ReviewRequest reviewRequest) {
        if (!orderCheckPort.isOrdered(reviewRequest.getOrderId())) {
             return ;
        }

        Review review = ReviewMapper.fromRequest(reviewRequest);
        reviewRepository.save(review);
    }

    public List<ReviewResponse> getReviews(Long customerId, Pageable pageable) {
        List<Review> reviewsByCustomerId = reviewRepository.getReviewsByCustomerId(customerId, pageable);
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : reviewsByCustomerId) {
            reviewResponses.add(ReviewMapper.toResponse(review));
        }
        return reviewResponses;
    }


    public List<ReviewResponse> search(ReviewSearchRequest request) {
        SearchHits<ReviewDocument> search = reviewRepository.search(request);

        List<ReviewResponse> contents = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(a -> ReviewMapper.toResponse(ReviewMapper.fromDocument(a)))
                .toList();
        return contents;
    }
}
