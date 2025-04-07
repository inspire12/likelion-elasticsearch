package com.inspire12.likelionelasticsearch.module.review.support;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;

public class ReviewMapper {
    private ReviewMapper() {}

    public static Review fromRequest(ReviewRequest request) {
        return Review.builder().build();
    }

    public static ReviewDocument toEntity(Review review) {


        return new ReviewDocument();
    }
}
