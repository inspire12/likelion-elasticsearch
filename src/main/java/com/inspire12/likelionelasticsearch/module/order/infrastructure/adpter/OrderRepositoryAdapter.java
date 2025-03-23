package com.inspire12.likelionelasticsearch.module.order.infrastructure.adpter;

import com.inspire12.likelionelasticsearch.exception.OrderNotExistException;
import com.inspire12.likelionelasticsearch.module.order.application.port.out.StoreStatusPort;
import com.inspire12.likelionelasticsearch.module.order.domain.Order;
import com.inspire12.likelionelasticsearch.module.order.domain.OrderRepository;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.document.OrderDocument;
import com.inspire12.likelionelasticsearch.module.order.infrastructure.repository.OrderEsRepository;
import com.inspire12.likelionelasticsearch.module.order.support.factory.OrderFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderEsRepository orderEsRepository;
    private final StoreStatusPort statusPort;

    public OrderRepositoryAdapter(OrderEsRepository orderEsRepository, StoreStatusPort statusPort) {
        this.orderEsRepository = orderEsRepository;
        this.statusPort = statusPort;
    }

    @Override
    public Order getOrderById(Long orderId) {
        if (statusPort.getStoreOpenStatus(orderId)) {
            OrderDocument orderDocument = orderEsRepository.findById(orderId).orElseThrow(OrderNotExistException::new);

            return OrderFactory.createOrder(orderDocument);
        }
        throw new OrderNotExistException();
    }


    @Override
    public Order getOrderByOrderNumber(String orderNumber) {
//        OrderEntity orderEntity = orderJpaRepository.findByOrderNumber(orderNumber).orElseThrow(OrderNotExistException::new);
//        return OrderFactory.createOrder(orderEntity);
        throw new OrderNotExistException();
    }

    @Override
    public Order save(Order order) {
        OrderDocument orderDocument = OrderFactory.createOrderDocument(order);
        OrderDocument orderDocumentSaved = orderEsRepository.save(orderDocument);
        return OrderFactory.createOrder(orderDocumentSaved);
    }
}
