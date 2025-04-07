package com.inspire12.likelionelasticsearch.module.order.application.service;

import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    Order findById(Long orderId);


    Order getOrderByOrderNumber(String orderNumber);

    Order save(Order order);

    void deleteById(Long orderId);

    @Transactional
    Order updateTotalAmount(Long orderId, Integer totalAmount);

    List<Order> findAllBy(Pageable pageable);
}
