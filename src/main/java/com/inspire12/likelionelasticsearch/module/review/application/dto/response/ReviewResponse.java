package com.inspire12.likelionelasticsearch.module.review.application.dto.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    @JsonSerialize(using = )
    private Map<String, Object> dynamicFilters = new HashMap<>();

    @JsonAnySetter
    public void setDynamic(String key, Object value) {
        // 고정 필드를 제외한 나머지를 동적 필터로 분리
        if (!List.of("keyword", "sort", "page", "size").contains(key)) {
            dynamicFilters.put(key, value);
        }
    }
}
