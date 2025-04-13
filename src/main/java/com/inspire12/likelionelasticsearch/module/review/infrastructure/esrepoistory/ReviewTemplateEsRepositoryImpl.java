package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;

// Elasticsearch Core (org.elasticsearch.*)

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.ScriptScoreQuery;
import co.elastic.clients.json.JsonData;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;


// Spring Data Elasticsearch
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;
import co.elastic.clients.elasticsearch._types.Script;


// Java 기본 API
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class ReviewTemplateEsRepositoryImpl implements ReviewTemplateEsRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public ReviewTemplateEsRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

//    @Override
//    public SearchHits<ReviewDocument> search(ReviewSearchRequest request) {
//        // TODO content(review) 에서 확인, 다양한 패턴으로 검색 해보기
//        Criteria criteria = new Criteria("content").matches(request.getKeyword());
////                .and(new Criteria("storeId").is(request.));
//        CriteriaQuery query = new CriteriaQuery(criteria);
//        query.setPageable(PageRequest.of(request.getPage(), request.getSize()));
//
//        SearchHits<ReviewDocument> hits = elasticsearchOperations.search(query, ReviewDocument.class);
//        return hits;
//    }


//    searchByNamedQuery
    @Override
    public SearchHits<ReviewDocument> search(ReviewSearchRequest request) {
        // TODO content(review) 에서 확인, 다양한 패턴으로 검색 해보기
        String wildcardIndex = "reviews-2025-*";

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> b
                                .must(m -> m.match(mt -> mt.field("content").query(request.getKeyword())))
//                                .filter(f -> f.range(r -> r.number(r1 -> r1.field("rating").gte(4.0))))
//                                .filter(f -> f.range(r -> r.date(r2 -> r2.field("date").gte("now-1M/M"))))
                        ))

//                     .withSort(Sort.by(Sort.Direction.DESC, "rating"))
                .build();

        nativeQuery.setPageable(PageRequest.of(request.getPage(), request.getSize()));

        SearchHits<ReviewDocument> hits = elasticsearchOperations.search(nativeQuery, ReviewDocument.class, IndexCoordinates.of(wildcardIndex));
        return hits;
    }

    @Override
    public SearchHits<ReviewDocument> searchByUserInfo(ReviewSearchRequest request) {
        String wildcardIndex = "reviews*";
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q.term(t -> t.field("userInfo.username.keyword").value("inspire12")))
                .build();
        nativeQuery.setPageable(PageRequest.of(request.getPage(), request.getSize()));

        SearchHits<ReviewDocument> hits = elasticsearchOperations.search(nativeQuery, ReviewDocument.class, IndexCoordinates.of(wildcardIndex));
        return hits;
    }
    //searchByStringQuery
//    @Override
//    public SearchHits<ReviewDocument> search(ReviewSearchRequest request) {
//        // TODO content(review) 에서 확인, 다양한 패턴으로 검색 해보기
//
//        String jsonQuery = """
//                {
//                    "bool": {
//                        "must": [
//                        { "prefix" : { "content": "맛있" } },
//                      { "range": { "rating": { "gte": 4 } } }
//                    ],
//                    "filter": [
//                      { "term": { "storeId": 101 } }
//                    ]
//                  }
//                }
//                """.formatted(request.getKeyword());
////{
////  "bool": {
////    "must": [{"match": {"content": "%s"}}],
////    "filter": [
////      {"range": {"rating": {"gte": 4.0}}},
////      {"range": {"date": {"gte": "now-1M/M"}}}
////    ]
////  }
////}
//        Query nativeQuery = new StringQuery(jsonQuery);
//        SearchHits<ReviewDocument> hits = elasticsearchOperations.search(nativeQuery, ReviewDocument.class);
//        return hits;
//    }

    @Override
    public void saveBulk(List<ReviewDocument> reviews) {
        if (reviews.isEmpty()) {
            return;
        }
        String indexName = "reviews-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

        List<IndexQuery> queries = reviews.stream()
                .map(review -> new IndexQueryBuilder()
                        .withIndex(indexName)
                        .withObject(review)
                        .build())
                .collect(Collectors.toList());
        elasticsearchOperations.bulkIndex(queries, ReviewDocument.class);
    }

    @Override
    public ReviewDocument saveWithIndex(ReviewDocument reviewDocument) {
        // 특수문자 제거
        String cleanedContent = reviewDocument.getContent().replaceAll("[^가-힣a-zA-Z0-9\\s]", "");
        // 길이 제한
        if (cleanedContent.length() > 1000) {
            cleanedContent = cleanedContent.substring(0, 1000);
        }
        reviewDocument.setClearContent(cleanedContent);
        // index pattern 날짜별
        String indexName = "reviews-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        elasticsearchOperations.save(reviewDocument, IndexCoordinates.of(indexName));

        return reviewDocument;
    }


    @Override
    public List<ReviewDocument> searchSimilarReviews(ReviewDocument reviewDocument, int topK) {
//        float[] queryEmbedding = embeddingService.getEmbedding(ReviewMapper.toEntity(review)).getOutput();

        ScriptScoreQuery scriptScoreQuery = QueryBuilders.scriptScore()
                .script(Script.of(s -> s

                ))
                .build();
                                NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(q -> q.scriptScore(ss -> ss
                        .script(script -> script
                                .source("cosineSimilarity(params.query_vector, 'embedding') + 1.0")
                                .params(Map.of("query_vector", JsonData.fromJson(reviewDocument.toString())))
                        )
                ))
                .withPageable(PageRequest.of(0, topK))
                .build();

        return elasticsearchOperations.search(searchQuery, ReviewDocument.class)
                .map(SearchHit::getContent)
                .toList();
    }
}
