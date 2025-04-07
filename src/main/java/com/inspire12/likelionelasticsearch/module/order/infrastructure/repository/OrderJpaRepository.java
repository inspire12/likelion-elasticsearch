package com.inspire12.likelionelasticsearch.module.order.infrastructure.repository;

import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderSum;
import com.inspire12.likelionelasticsearch.module.order.application.dto.OrderSummary;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where o.id=:orderId")
    Optional<OrderEntity> findByOrderId(Long orderId);

    @Query("select o from OrderEntity o where o.orderNumber=:orderNumber")
    Optional<OrderEntity> findByOrderNumber(String orderNumber);


    @Query("SELECT new com.inspire12.likelionelasticsearch.module.jpa.model.dto.OrderSummary(o.id, o.orderNumber, o.totalAmount) " +
            "FROM OrderEntity o WHERE o.customerId = :customerId")
    List<OrderSummary> findOrderSummariesByCustomerId(@Param("customerId") Long customerId);


    @Query("select new com.inspire12.likelionelasticsearch.module.jpa.model.dto.OrderSum(o.customerId, sum(o.totalAmount)) from OrderEntity o where o.customerId=:customerId")
    OrderSum sumAmountByUserId(Long customerId);
}
