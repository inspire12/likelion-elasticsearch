package com.inspire12.likelionelasticsearch.module.review.application.service;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.ReviewResponse;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.SearchResponse;
import com.inspire12.likelionelasticsearch.module.review.application.port.out.OrderCheckPort;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.domain.ReviewRepository;
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

    public void saveReview(ReviewRequest reviewRequest) {
        if (!orderCheckPort.isOrdered(reviewRequest.getOrderId())) {
            return;
        }
        Review review = ReviewMapper.fromRequest(reviewRequest);
        reviewRepository.save(review);
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
        SearchHits<ReviewDocument> search = reviewRepository.search(request);

        List<ReviewResponse> contents = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(a -> ReviewMapper.toResponse(ReviewMapper.fromDocument(a)))
                .toList();
        return SearchResponse.of(contents, search.getTotalHits(), request.getPage(), search.getSearchHits().size());
    }

    public SearchResponse<ReviewResponse> searchByUserInfo(ReviewSearchRequest request) {
        SearchHits<ReviewDocument> search = reviewRepository.searchByUserInfo(request);

        List<ReviewResponse> contents = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(a -> ReviewMapper.toResponse(ReviewMapper.fromDocument(a)))
                .toList();
        return SearchResponse.of(contents, search.getTotalHits(), request.getPage(), search.getSearchHits().size());
    }



}
