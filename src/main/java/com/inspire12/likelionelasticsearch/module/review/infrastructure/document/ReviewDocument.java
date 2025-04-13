package com.inspire12.likelionelasticsearch.module.review.infrastructure.document;


import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//@ToString
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

    @Field(type = FieldType.Text, analyzer = "nori")
    private String content;

    @Field(type = FieldType.Integer)
    private Integer rating;

    @Field(type = FieldType.Keyword)
    private String sentiment;

    // TODO
    @Field(type = FieldType.Object, includeInParent = true)
    private UserInfoSubDocument userInfo;

    @Field(type = FieldType.Object, includeInParent = true)
    private StoreInfoSubDocument storeInfo;


//     TODO 리스트 형태라면 Nested 타입으로 사용해야한다.
//    @Field(type = FieldType.Nested, includeInParent = true)
//    private List<UserInfoSubDocument> userInfos;

    @Field(type = FieldType.Dense_Vector, dims = 1536)
    private float[] embedding; // Embedding 벡터 필드

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    //@Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updatedAt;


    public void setEmbeddingFromOpenAi(float[] embedding) {
        this.embedding = embedding;
    }

    public void setClearContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "ReviewDocument{" +
                "id='" + id + '\'' +
                ", orderId=" + orderId +
                ", storeId=" + storeId +
                ", customerId=" + customerId +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", 중요! sentiment='" + sentiment + '\'' +
                ", userInfo=" + userInfo +
                ", storeInfo=" + storeInfo +
                ", embedding=" + Arrays.toString(embedding) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

