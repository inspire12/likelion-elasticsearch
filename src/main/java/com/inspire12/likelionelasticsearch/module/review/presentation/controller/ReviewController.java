package com.inspire12.likelionelasticsearch.module.review.presentation.controller;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.ReviewListResponse;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.ReviewResponse;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.SearchResponse;
import com.inspire12.likelionelasticsearch.module.review.application.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public SearchResponse<ReviewResponse> getReviews(@RequestParam Long customerId, Pageable pageable) {
        return reviewService.getReviews(customerId, pageable);
    }

    @PostMapping("/search")
    public SearchResponse<ReviewResponse> search(@RequestBody ReviewSearchRequest request) {
        return reviewService.search(request);
    }

    @PostMapping("/search/userInfo")
    public SearchResponse<ReviewResponse> searchByUserInfo(@RequestBody ReviewSearchRequest request) {
        return reviewService.searchByUserInfo(request);
    }

    @PostMapping
    public void saveReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.saveReview(reviewRequest);
    }

    @PostMapping("/bulk")
    public void saveReviews(@RequestBody ReviewRequest reviewRequest) {
        reviewService.saveReviewBulk(reviewRequest);
    }

//    // ID로 리뷰 조회 API
//    @GetMapping("/{id}")
//    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long id) {
//    }
//    // 리뷰 저장 API
//
//
//    // 키워드로 리뷰 검색 API (content 필드 기준)
//    @GetMapping("/search")
//    public ResponseEntity<> searchReviews(@RequestParam String keyword) {
//
//        return ResponseEntity.ok(results);
//    }

}
