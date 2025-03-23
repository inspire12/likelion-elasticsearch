package com.inspire12.likelionelasticsearch.module.order.application.dto;


import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private UUID orderNumber;
    private OrderStatus orderStatus;
}
