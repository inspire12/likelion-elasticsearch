package com.inspire12.likelionelasticsearch.module.order.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderSummary {
    private Long id;
    private String orderNumber;
    private Integer totalAmount;
}