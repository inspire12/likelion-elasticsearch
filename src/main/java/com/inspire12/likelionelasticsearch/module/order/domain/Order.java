package com.inspire12.likelionelasticsearch.module.order.domain;

import com.inspire12.likelionelasticsearch.module.order.domain.constant.DeliveryStatus;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderStatus;
import com.inspire12.likelionelasticsearch.module.order.domain.constant.OrderType;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;

    @Setter
    private OrderStatus orderStatus;

    private UUID orderNumber;

    private Long customerId;

    private Long totalOrderAmount;
    private Long totalPayedAmount;

    private Long storeId;

    // Delivery
    private OrderType orderType;
    @Nullable
    private Long deliveryId;
    @Nullable
    private DeliveryStatus deliveryStatus;

    private Long deliveryPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public void approvePayment(boolean isPaymentSuccess) {
        if (isPaymentSuccess) {
            this.setOrderStatus(OrderStatus.SUCCESS_PAYMENT);
        } else {
            this.setOrderStatus(OrderStatus.FAIL_PAYMENT);
        }
    }

}
