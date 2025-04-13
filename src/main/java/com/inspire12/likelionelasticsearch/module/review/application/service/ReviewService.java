package com.inspire12.likelionelasticsearch.module.review.application.service;

import com.inspire12.likelionelasticsearch.exception.OrderNotExistException;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.ReviewResponse;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.SearchResponse;
import com.inspire12.likelionelasticsearch.module.review.application.port.out.OrderCheckPort;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.application.port.in.ReviewRepository;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import com.inspire12.likelionelasticsearch.module.review.support.ReviewMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    public ReviewDocument saveReview(ReviewRequest reviewRequest) {
        if (!orderCheckPort.isOrdered(reviewRequest.getOrderId())) {
            throw new OrderNotExistException();
        }
        Review review = ReviewMapper.fromRequest(reviewRequest);
        return reviewRepository.save(review);
    }

    public void saveReviewBulk(ReviewRequest reviewRequest) {
//        reviewRequests.
        List<ReviewRequest> reviewRequests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            reviewRequests.add(reviewRequest);
        }
        reviewRepository.saveBulk(reviewRequests);
    }

    public SearchResponse<ReviewResponse> getReviews(Long customerId, Pageable pageable) {
        Page<Review> reviewsByCustomerId = reviewRepository.getReviewsByCustomerId(customerId, pageable);
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : reviewsByCustomerId) {
            reviewResponses.add(ReviewMapper.toResponse(review));
        }
        PageImpl<ReviewResponse> reviews = new PageImpl<>(reviewResponses, reviewsByCustomerId.getPageable(), reviewsByCustomerId.getTotalElements());
        return SearchResponse.of(reviews);
    }


    public SearchResponse<ReviewResponse> search(ReviewSearchRequest request) {
        Page<Review> search = reviewRepository.search(request);

        List<ReviewResponse> contents = search.stream()
                .map(ReviewMapper::toResponse)
                .toList();
        return SearchResponse.of(contents, search.getTotalElements(), request.getPage(), search.getSize());
    }

    public SearchResponse<ReviewResponse> searchByUserInfo(ReviewSearchRequest request) {
        Page<Review> search = reviewRepository.searchByUserInfo(request);

        List<ReviewResponse> contents = search.stream()
                .map(ReviewMapper::toResponse)
                .toList();
        return SearchResponse.of(contents, search.getTotalElements(), request.getPage(), search.getSize());
    }

    public SearchResponse<ReviewResponse> searchMatching(ReviewRequest request) {
        List<Review> reviews = reviewRepository.searchByVector(request);

        List<ReviewResponse> contents = reviews.stream()
                .map(ReviewMapper::toResponse)
                .toList();
        return SearchResponse.of(contents, 0, 0, 0);
    }

}
