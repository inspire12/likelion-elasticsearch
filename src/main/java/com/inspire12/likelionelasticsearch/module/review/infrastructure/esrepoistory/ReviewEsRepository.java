package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;


import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewEsRepository extends ElasticsearchRepository<ReviewDocument, Long> {


}
