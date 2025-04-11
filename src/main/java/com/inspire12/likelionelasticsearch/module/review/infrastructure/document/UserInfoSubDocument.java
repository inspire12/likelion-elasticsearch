package com.inspire12.likelionelasticsearch.module.review.infrastructure.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoSubDocument {
    @Field(type = FieldType.Keyword)
    private String userId;

    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Keyword)
    private String email;

}