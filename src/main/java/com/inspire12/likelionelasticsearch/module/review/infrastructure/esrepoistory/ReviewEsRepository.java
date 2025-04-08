package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;


import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewEsRepository extends ElasticsearchRepository<ReviewDocument, Long>
        // TODO 확인, JPA 할 때 QueryDSL 처럼 RestClient 같은 raw한 형태로 api를 날리는 부분을 통합
        , ReviewTemplateEsRepository {
    Page<ReviewDocument> findAllByCustomerId(Long customerId, Pageable pageable);
}
