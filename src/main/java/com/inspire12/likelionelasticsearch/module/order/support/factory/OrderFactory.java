package com.inspire12.likelionelasticsearch.module.order.support.factory;

import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderRequest;
import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderResponse;
import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderStatus;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.document.OrderDocument;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.repository.entity.OrderEntity;

import java.util.UUID;

public class OrderFactory {
    public static OrderResponse createOrderResponse(Order order) {
        return OrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .orderNumber(order.getOrderNumber())
                .build();
    }


    public static Order createOrder(OrderRequest orderRequest, UUID orderNumber, OrderStatus orderStatus) {
        return Order.builder()
                .orderNumber(orderNumber)
                .orderStatus(orderStatus)
                .customerId(orderRequest.getCustomerId())
                .storeId(orderRequest.getStoreId())
                .orderType(orderRequest.getOrderType())
                .build();
    }

    public static Order createOrder(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .storeId(orderEntity.getStoreId())
                .createdAt(orderEntity.getCreatedAt())
                .updatedAt(orderEntity.getUpdatedAt())
                .orderStatus(orderEntity.getOrderStatus())
                .orderNumber(orderEntity.getOrderNumber())
                .build();
    }

    public static Order createOrder(OrderDocument orderDocument) {
        return Order.builder()
                .id(orderDocument.getId())
                .storeId(orderDocument.getStoreId())
                .createdAt(orderDocument.getCreatedAt())
                .updatedAt(orderDocument.getUpdatedAt())
                .orderStatus(orderDocument.getOrderStatus())
                .orderNumber(orderDocument.getOrderNumber())
                .build();
    }

    public static OrderDocument createOrderDocument(Order order) {
        return new OrderDocument(
                order.getId(),
                order.getCustomerId(),
                order.getStoreId(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getOrderStatus(),
                order.getOrderNumber());
    }
}
