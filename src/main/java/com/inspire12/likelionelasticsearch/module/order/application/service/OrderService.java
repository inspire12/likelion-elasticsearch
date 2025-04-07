package com.inspire12.likelionelasticsearch.module.order.application.service;

import com.inspire12.likelionelasticsearch.exception.OrderNotExistException;
import com.inspire12.likelionelasticsearch.module.order.application.dto.request.OrderRequest;
import com.inspire12.likelionelasticsearch.module.order.application.dto.response.OrderListResponse;
import com.inspire12.likelionelasticsearch.module.order.application.dto.response.OrderResponse;
import com.inspire12.likelionelasticsearch.module.order.application.port.out.StoreStatusPort;
import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import com.inspire12.likelionelasticsearch.module.order.support.mapper.OrderMapper;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreStatusPort statusPort;

    public OrderService(OrderRepository orderRepository, StoreStatusPort statusPort) {
        this.orderRepository = orderRepository;
        this.statusPort = statusPort;
    }


    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (!statusPort.getStoreOpenStatus(order.getStoreId())) {
            throw new OrderNotExistException();
        }
        return OrderMapper.toResponse(order);
    }

    public OrderListResponse getOrders(Pageable pageable) {
        List<Order> orders = orderRepository.findAllBy(pageable);
        return OrderMapper.toListResponse(orders);
    }


    public OrderResponse saveOrder(OrderRequest request) {
        Order order = orderRepository.save(OrderMapper.toDomain(request));
        return OrderMapper.toResponse(orderRepository.save(order));
    }


    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }


    public OrderResponse updateTotalAmount(Long orderId, Integer newAmount) {
        Order order = orderRepository.updateTotalAmount(orderId, newAmount);
        return OrderMapper.toResponse(order);
    }

}
