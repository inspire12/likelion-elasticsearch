package com.inspire12.likelionelasticsearch.module.order.application.service;

import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderRequest;
import com.inspire12.likelionelasticsearch.exception.OrderNotExistException;
import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderResponse;
import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import com.inspire12.likelionelasticsearch.module.order.domain.OrderRepository;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderStatus;
import com.inspire12.likelionelasticsearch.module.order.support.factory.OrderFactory;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());
    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }


    public Order orderToPayment(OrderRequest orderRequest) {

//        Order order = orderRepository.save(shoppingCart, orderRequest);
//        return order
        throw new OrderNotExistException();
    }


    public Order getOrderByOrderNumber(String orderNumber) {
        return orderRepository.getOrderByOrderNumber(orderNumber);
    }

    public OrderResponse saveOrder(OrderRequest orderRequest) {
        UUID orderNumber = UUID.randomUUID();
        Order order = OrderFactory.createOrder(orderRequest, orderNumber, OrderStatus.PENDING_PAYMENT);
        orderRepository.save(order);
        log.info("{}", order.getId());
        return OrderFactory.createOrderResponse(orderRepository.save(order));
    }
}
