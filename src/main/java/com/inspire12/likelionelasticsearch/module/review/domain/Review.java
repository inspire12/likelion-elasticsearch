package com.inspire12.likelionelasticsearch.module.review.domain;

import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.UserInfoSubDocument;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {
    private String review;
    private Long customerId;
    private Long orderId;
    private Long storeId;
    private Integer rating;
    private UserInfoSubDocument userInfo;
}
