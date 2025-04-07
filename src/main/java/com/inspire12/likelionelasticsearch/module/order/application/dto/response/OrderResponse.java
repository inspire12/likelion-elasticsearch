package com.inspire12.likelionelasticsearch.module.order.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long customerId;
    private String orderNumber;
    private Integer totalAmount;
    private LocalDateTime createdAt;

}

