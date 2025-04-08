package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;


import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewTemplateEsRepository {
    Page<ReviewDocument> search(ReviewSearchRequest request);
}