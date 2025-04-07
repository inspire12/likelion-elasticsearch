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
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId; // 주문과 연결

    @Column(nullable = false)
    private Long customerId; // 작성자 정보

    @Column(nullable = false)
    private Long restaurantId; // 맛집 ID

    @Column(nullable = false, length = 500)
    private String reviewContent; // 리뷰 내용 최소 10자

    @Column(nullable = false)
    private int rating; // 평점 (1~5점)

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}