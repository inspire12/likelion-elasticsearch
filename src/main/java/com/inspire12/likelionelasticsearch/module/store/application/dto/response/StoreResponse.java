package com.inspire12.likelionelasticsearch.module.store.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {

    private String name;

    private String category;

    private List<String> menus;

    private float rating;

    private String location;

    private String description;
}
