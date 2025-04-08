package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;


@Repository
public class ReviewTemplateEsRepositoryImpl implements ReviewTemplateEsRepository {

    private final ElasticsearchClient elasticsearchClient;

    public ReviewTemplateEsRepositoryImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @Override
    public Page<ReviewDocument> search(ReviewSearchRequest request) {
        // TODO
        //        elasticsearchClient.search()
        return null;
    }
}
