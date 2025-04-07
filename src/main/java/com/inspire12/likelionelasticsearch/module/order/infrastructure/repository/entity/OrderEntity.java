package com.inspire12.likelionelasticsearch.module.order.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;    // 필수 칼럼으로 다시 추가됨

    @Column(unique = true, nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private Integer totalAmount;

    private LocalDateTime createdAt;

    // 변경 메서드 (명시적 메서드로만 수정 허용)
    public void changeTotalAmount(Integer newAmount) {
        this.totalAmount = newAmount;
    }
}