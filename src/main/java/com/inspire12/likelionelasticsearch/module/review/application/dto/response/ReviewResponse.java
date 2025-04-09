package com.inspire12.likelionelasticsearch.module.review.application.dto.response;

import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.UserInfoSubDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private String review;
    private Long customerId;
    private Long orderId;
    private Long storeId;
    private Integer rating;
    private UserInfoSubDocument userInfo;
}
