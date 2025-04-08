package com.inspire12.likelionelasticsearch.module.review.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class Review {
    private String review;
    private Long customerId;
    private Long orderId;
    private Long storeId;
    private Integer rating;

    private Map<String, Object> dynamicFilters = new HashMap<>();

}
