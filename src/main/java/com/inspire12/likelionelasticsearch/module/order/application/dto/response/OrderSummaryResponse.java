package com.inspire12.likelionelasticsearch.module.order.application.dto.response;

import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryResponse {
    private List<OrderSummary> orderSummaries;
}
