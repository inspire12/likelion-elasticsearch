package com.inspire12.likelionelasticsearch.module.review.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchResponse<T> {
    private List<T> content;   // 실제 데이터 목록
    private long totalElements; // 전체 검색 결과 수
    private int page;           // 현재 페이지
    private int size;           // 페이지 사이즈

    public static <T> SearchResponse of(Page<T> content) {
        return new SearchResponse(content.getContent(), content.getTotalElements(), content.getPageable().getPageNumber(), content.getPageable().getPageSize());
    }
}