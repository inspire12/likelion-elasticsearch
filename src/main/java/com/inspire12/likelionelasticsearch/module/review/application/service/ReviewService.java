package com.inspire12.likelionelasticsearch.module.review.application.service;

import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewRequest;
import com.inspire12.likelionelasticsearch.module.review.application.port.out.OrderCheckPort;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.domain.ReviewRepository;
import com.inspire12.likelionelasticsearch.module.review.support.ReviewMapper;
import org.springframework.stereotype.Service;

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
}
