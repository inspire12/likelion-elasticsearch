package com.inspire12.likelionelasticsearch.module.order.application.service;

import com.inspire12.likelionelasticsearch.module.review.application.port.out.OrderCheckPort;
import org.springframework.stereotype.Service;

@Service
public class OrderCheckUsecase implements OrderCheckPort {

    @Override
    public boolean isOrdered(long orderId) {
        return true;
    }
}
