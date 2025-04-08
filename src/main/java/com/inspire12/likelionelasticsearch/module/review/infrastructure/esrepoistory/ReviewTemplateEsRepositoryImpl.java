package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;

// Elasticsearch Core (org.elasticsearch.*)
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;


// Spring Data Elasticsearch
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;

// Java 기본 API
import java.util.List;


@Repository
public class ReviewTemplateEsRepositoryImpl implements ReviewTemplateEsRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public ReviewTemplateEsRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public SearchHits<ReviewDocument> search(ReviewSearchRequest request) {
        Criteria criteria = new Criteria("content").matches(request.getKeyword());
//                .and(new Criteria("storeId").is(request.));

        CriteriaQuery query = new CriteriaQuery(criteria);
        query.setPageable(PageRequest.of(0, 10));

        SearchHits<ReviewDocument> hits = elasticsearchOperations.search(query, ReviewDocument.class);

        return hits;
    }
}
