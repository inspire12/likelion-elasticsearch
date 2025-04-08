package com.inspire12.likelionelasticsearch.module.review.support;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.dto.response.ReviewResponse;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;

public class ReviewMapper {
    private ReviewMapper() {}

    public static Review fromRequest(ReviewRequest request) {
        return Review.builder().build();
    }

    public static ReviewDocument toEntity(Review review) {
        return ReviewDocument.builder()
                .orderId(review.getOrderId())
                .rating(review.getRating())
                .content(review.getReview())
                .storeId(review.getStoreId())
                .customerId(review.getCustomerId())
                .build();
    }

    public static Review fromDocument(ReviewDocument reviewDocument) {
        return Review.builder()

                .orderId(reviewDocument.getOrderId())
                .rating(reviewDocument.getRating())
                .review(reviewDocument.getContent())
                .storeId(reviewDocument.getStoreId())
                .customerId(reviewDocument.getCustomerId())
                .build();
    }

    public static ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
                .orderId(review.getOrderId())
                .rating(review.getRating())
                .review(review.getReview())
                .storeId(review.getStoreId())
                .customerId(review.getCustomerId())
                .build();
    }
}
