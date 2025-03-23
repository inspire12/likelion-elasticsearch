package com.inspire12.likelionelasticsearch.module.order.application.dto;


import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long customerId;
    private Long storeId;
    private OrderType orderType;

}
