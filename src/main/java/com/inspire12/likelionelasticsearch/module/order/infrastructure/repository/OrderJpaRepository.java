package com.inspire12.likelionelasticsearch.module.order.infrastructure.repository;

import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

// TODO JPA 연동 이후 변경 예정
@Component
public interface OrderJpaRepository  {

    Order findByOrderNumber(UUID uuid);
}
