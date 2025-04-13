package com.inspire12.likelionelasticsearch.module.store.infrastructure.document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "store")
public class StoreDocument {

    @Id
    @Field(type = FieldType.Keyword, name = "id")
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private List<String> menus;

    @Field(type = FieldType.Float)
    private float rating;

    @Field(type = FieldType.Text)
    private String location;

    @Field(type = FieldType.Dense_Vector, dims = 1536)
    private float[] vector;

    @Field(type = FieldType.Text)
    private String description;

    public void setVectorFromOpenAi(float[] vector) {
        this.vector = vector;
    }


    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", category='" + category + '\'' +
                ", menus=" + menus +
                ", rating=" + rating +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
