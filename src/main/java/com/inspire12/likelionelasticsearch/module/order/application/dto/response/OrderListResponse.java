package com.inspire12.likelionelasticsearch.module.order.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse {
    List<OrderResponse> orderResponses;
}
