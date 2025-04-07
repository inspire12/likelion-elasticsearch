package com.inspire12.likelionelasticsearch.module.review.presentation.controller;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public void reviews() {

    }

    @PostMapping
    public void saveReviews(@RequestBody ReviewRequest reviewRequest) {
        reviewService.saveReview(reviewRequest);
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
