package com.inspire12.likelionelasticsearch.module.store.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {

    private String name;

    private String category;

    private List<String> menus;

    private float rating;

    private String location;

    private String description;
}
