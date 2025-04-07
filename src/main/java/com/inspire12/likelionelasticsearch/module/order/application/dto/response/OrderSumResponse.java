package com.inspire12.likelionelasticsearch.module.order.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSumResponse {
    private Long customerId;
    private Long sum;
}
