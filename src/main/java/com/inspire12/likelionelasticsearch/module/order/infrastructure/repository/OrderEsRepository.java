package com.inspire12.likelionelasticsearch.module.order.infrastructure.repository;


import com.inspire12.likelionelasticsearch.module.order.infrastructure.document.OrderDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderEsRepository extends ElasticsearchRepository<OrderDocument, Long> {

    Optional<OrderDocument> findByOrderNumber(String orderNumber);
}
