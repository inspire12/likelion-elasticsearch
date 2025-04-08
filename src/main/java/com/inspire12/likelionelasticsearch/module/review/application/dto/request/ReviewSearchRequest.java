package com.inspire12.likelionelasticsearch.module.review.application.dto.request;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSearchRequest {

    private String keyword;         // 검색 키워드 (본문에서 match)
    private String sort = "latest"; // 정렬: latest, oldest
    private Integer page = 0;       // 페이지 번호
    private Integer size = 10;      // 페이지 사이즈

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
