package com.inspire12.likelionelasticsearch.module.review.application.dto.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private String review;
    private Long customerId;
    private Long orderId;
    private Long storeId;
    private Integer rating;

    @JsonAnySetter
    Map<String, Object> dynamicField = new HashMap<>();
}
