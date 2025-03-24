package com.inspire12.likelionelasticsearch.module.order.infrastructure.document;

import com.inspire12.likelionelasticsearch.module.order.domain.constant.DeliveryStatus;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderStatus;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Document(indexName = "order")
@NoArgsConstructor
@AllArgsConstructor

public class OrderDocument {
    @Id
    private UUID orderNumber;

    private Long customerId;
    private Long storeId;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private OrderStatus orderStatus;


}
