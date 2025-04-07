package com.inspire12.likelionelasticsearch.module.order.support.mapper;

import com.inspire12.likelionelasticsearch.module.order.application.dto.request.OrderRequest;
import com.inspire12.likelionelasticsearch.module.order.application.dto.response.OrderListResponse;
import com.inspire12.likelionelasticsearch.module.order.application.dto.response.OrderResponse;
import com.inspire12.likelionelasticsearch.module.order.application.dto.response.OrderSumResponse;
import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderStatus;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderType;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.repository.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class OrderMapper {

    private OrderMapper() {
    }

    // Domain (Order) -> Response
    public static OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .orderNumber(order.getOrderNumber().toString())
                .totalAmount(order.getTotalOrderAmount().intValue())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static OrderListResponse toListResponse(List<Order> orders) {

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(OrderMapper.toResponse(order));
        }
        return new OrderListResponse(orderResponses);
    }

    // Entity -> Domain (Order)
    public static Order fromEntity(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .orderNumber(UUID.fromString(orderEntity.getOrderNumber()))
                .customerId(orderEntity.getCustomerId())
                .totalOrderAmount(orderEntity.getTotalAmount().longValue())
                .createdAt(orderEntity.getCreatedAt())
                .build();
    }

    // Domain (Order) -> Entity
    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber().toString())
                .customerId(order.getCustomerId())
                .totalAmount(order.getTotalOrderAmount().intValue())
                .createdAt(order.getCreatedAt())
                .build();
    }

    // List<Domain> -> Response List
    public static OrderListResponse fromDomain(List<Order> orders) {
        List<OrderResponse> responses = orders.stream()
                .map(OrderMapper::toResponse)
                .collect(Collectors.toList());

        return new OrderListResponse(responses);
    }

    public static Order toDomain(OrderRequest orderRequest) {
        return Order.builder()
                .customerId(orderRequest.getCustomerId())
                .storeId(orderRequest.getStoreId())
                .orderType(orderRequest.getOrderType())
                .totalOrderAmount(orderRequest.getTotalAmount().longValue())
                .orderNumber(UUID.randomUUID())
                .createdAt(java.time.LocalDateTime.now())
                .build();
    }
}
