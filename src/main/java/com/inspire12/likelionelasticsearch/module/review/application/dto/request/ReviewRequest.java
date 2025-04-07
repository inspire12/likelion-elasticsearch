package com.inspire12.likelionelasticsearch.module.review.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private String review;
    private Long customerId;
    private Long orderId;
    private Long storeId;
    private Integer rating;
}
