package com.inspire12.likelionelasticsearch.module.store.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    private String id;

    private String name;

    private String category;

    private List<String> menus;

    private float rating;

    private String location;

    private String description;
}
