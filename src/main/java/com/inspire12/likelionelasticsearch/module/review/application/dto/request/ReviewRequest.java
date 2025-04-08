package com.inspire12.likelionelasticsearch.module.review.application.dto.request;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
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

    @JsonIgnore
    private Map<String, Object> dynamicFilters = new HashMap<>();

    @JsonAnySetter
    public void setDynamic(String key, Object value) {
        // 고정 필드를 제외한 나머지를 동적 필터로 분리
        if (!List.of("keyword", "sort", "page", "size").contains(key)) {
            dynamicFilters.put(key, value);
        }
    }
}
