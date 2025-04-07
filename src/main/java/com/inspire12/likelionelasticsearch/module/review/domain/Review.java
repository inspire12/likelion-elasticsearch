package com.inspire12.likelionelasticsearch.module.review.domain;

import lombok.Builder;

@Builder
public class Review {
    private String review;
    private Long customerId;
    private Long orderId;
    private Long storeId;
    private Integer rating;


}
