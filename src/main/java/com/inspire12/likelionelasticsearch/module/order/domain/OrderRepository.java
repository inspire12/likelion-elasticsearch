package com.inspire12.likelionelasticsearch.module.order.domain;

import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {

    Order getOrderById(Long orderId);


    Order getOrderByOrderNumber(String orderNumber);

    Order save(Order order);
}
