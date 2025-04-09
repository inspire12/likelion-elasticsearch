package com.inspire12.likelionelasticsearch.module.review.infrastructure.document;


import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.core.suggest.Completion;

import java.time.LocalDateTime;

@Getter
@Document(indexName = "reviews")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDocument {

    @Id
    @Field(type = FieldType.Keyword, name = "id")
    private String id;

    @Field(type = FieldType.Long, nullValue = "0")
    private Long orderId;

    @Field(type = FieldType.Long)
    private Long storeId;

    @Field(type = FieldType.Long)
    private Long customerId;

    @Setter
    @Field(type = FieldType.Text, analyzer = "nori")
    private String content;

    @Field(type = FieldType.Integer)
    private Integer rating;

    @Field(type = FieldType.Keyword)
    private String sentiment;

    @CompletionField
    private Completion suggest;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    //@Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updatedAt;
}

