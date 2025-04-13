package com.inspire12.likelionelasticsearch.module.review.infrastructure.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfoSubDocument {
    @Field(type = FieldType.Keyword)
    private String storeId;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Nested)
    private List<String> menus;

    @Field(type = FieldType.Float)
    private float rating;

    @Field(type = FieldType.Text)
    private String location;

    @Field(type = FieldType.Text)
    private String description;

    @Override
    public String toString() {
        return "StoreInfoSubDocument{" +
                "storeId='" + storeId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", menus='" + menus + '\'' +
                ", rating=" + rating +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
