package com.inspire12.likelionelasticsearch.module.order.infrastructure.adpter;

import com.inspire12.likelionelasticsearch.exception.OrderNotExistException;
import com.inspire12.likelionelasticsearch.module.order.application.port.out.StoreStatusPort;
import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import com.inspire12.likelionelasticsearch.module.order.application.service.OrderRepository;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.repository.OrderJpaRepository;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.repository.entity.OrderEntity;
import com.inspire12.likelionelasticsearch.module.order.support.mapper.OrderMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    private final StoreStatusPort statusPort;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, StoreStatusPort statusPort) {
        this.orderJpaRepository = orderJpaRepository;
        this.statusPort = statusPort;
    }

    @Override
    public Order findById(Long orderId) {
        if (statusPort.getStoreOpenStatus(orderId)) {
            OrderEntity orderEntity = orderJpaRepository.findById(orderId).orElseThrow(OrderNotExistException::new);

            return OrderMapper.fromEntity(orderEntity);
        }
        throw new OrderNotExistException();
    }


    @Override
    public Order getOrderByOrderNumber(String orderNumber) {
        if (statusPort.getStoreOpenStatusByOrderNumber(orderNumber)) {
            OrderEntity orderEntity = orderJpaRepository.findByOrderNumber(orderNumber).orElseThrow(OrderNotExistException::new);
            return OrderMapper.fromEntity(orderEntity);
        }
        throw new OrderNotExistException();
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderMapper.toEntity(order);
        OrderEntity savedOrder = orderJpaRepository.save(orderEntity);
        return OrderMapper.fromEntity(savedOrder);
    }

    @Override
    @Transactional
    public void deleteById(Long orderId) {
         OrderEntity orderEntity = orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 없음"));
        orderJpaRepository.delete(orderEntity);
    }


    @Transactional
    @Override
    public Order updateTotalAmount(Long orderId, Integer totalAmount) {
        OrderEntity byOrderId = orderJpaRepository.findByOrderId(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 없음"));
        byOrderId.changeTotalAmount(totalAmount);
        return OrderMapper.fromEntity(byOrderId);
    }

    @Override
    public List<Order> findAllBy(Pageable pageable) {
        Page<OrderEntity> all = orderJpaRepository.findAll(pageable);
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : all) {
            orders.add(OrderMapper.fromEntity(orderEntity));
        }
        return orders;
    }
}
