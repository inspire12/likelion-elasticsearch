package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import java.util.List;


@Repository
public class ReviewTemplateEsRepositoryImpl implements ReviewTemplateEsRepository {
    private final ElasticsearchOperations operations;

    public ReviewTemplateEsRepositoryImpl(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    @Override
    public List<ReviewDocument> search(ReviewSearchRequest request) {
        // TODO

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // match keyword
        if (request.getKeyword() != null && !request.getKeyword().isBlank()) {
            boolQuery.must(QueryBuilders.matchQuery("content", request.getKeyword()));
        }

        // dynamic filters
        request.getDynamicFilters().forEach((key, value) -> {
            if (value instanceof Number) {
                boolQuery.filter(QueryBuilders.termQuery(key, value));
            } else {
                boolQuery.filter(QueryBuilders.termQuery(key, value.toString()));
            }
        });

        // 정렬
        SortBuilder<?> sort = SortBuilders
                .fieldSort("createdAt");

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withSorts(sort)
                .withPageable(PageRequest.of(request.getPage(), request.getSize()))
                .build();

        SearchHits<ReviewDocument> hits = operations.search(query, ReviewDocument.class);

        return hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
