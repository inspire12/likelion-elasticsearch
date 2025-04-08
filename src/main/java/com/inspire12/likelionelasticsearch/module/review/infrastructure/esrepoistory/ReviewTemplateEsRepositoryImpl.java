package com.inspire12.likelionelasticsearch.module.review.infrastructure.esrepoistory;

// Elasticsearch Core (org.elasticsearch.*)

import co.elastic.clients.json.JsonData;
import com.inspire12.likelionelasticsearch.module.review.application.dto.request.ReviewSearchRequest;
import com.inspire12.likelionelasticsearch.module.review.domain.Review;
import com.inspire12.likelionelasticsearch.module.review.infrastructure.document.ReviewDocument;


// Spring Data Elasticsearch
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

// Java 기본 API
import java.util.List;
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



    //searchByNamedQuery
//    @Override
//    public SearchHits<ReviewDocument> search(ReviewSearchRequest request) {
//        // TODO content(review) 에서 확인, 다양한 패턴으로 검색 해보기
//        NativeQuery nativeQuery = NativeQuery.builder()
//                .withQuery(q -> q
//                        .bool(b -> b
//                                .must(m -> m.match(mt -> mt.field("content").query(request.getKeyword())))
////                                .filter(f -> f.range(r -> r.number(r1 -> r1.field("rating").gte(4.0))))
////                                .filter(f -> f.range(r -> r.date(r2 -> r2.field("date").gte("now-1M/M"))))
//                        ))
////                .withSort(Sort.by(Sort.Direction.DESC, "rating"))
//                .build();
//
//        nativeQuery.setPageable(PageRequest.of(request.getPage(), request.getSize()));
//
//        SearchHits<ReviewDocument> hits = elasticsearchOperations.search(nativeQuery, ReviewDocument.class);
//        return hits;
//    }


    //searchByStringQuery
    @Override
    public SearchHits<ReviewDocument> search(ReviewSearchRequest request) {
        // TODO content(review) 에서 확인, 다양한 패턴으로 검색 해보기

        String jsonQuery = """
{
    "bool": {
        "must": [
        { "prefix" : { "content": "맛있" } },
      { "range": { "rating": { "gte": 4 } } }
    ],
    "filter": [
      { "term": { "storeId": 101 } }
    ]
  }
}
""".formatted(request.getKeyword());
//{
//  "bool": {
//    "must": [{"match": {"content": "%s"}}],
//    "filter": [
//      {"range": {"rating": {"gte": 4.0}}},
//      {"range": {"date": {"gte": "now-1M/M"}}}
//    ]
//  }
//}
        Query nativeQuery = new StringQuery(jsonQuery);
        SearchHits<ReviewDocument> hits = elasticsearchOperations.search(nativeQuery, ReviewDocument.class);
        return hits;
    }

    @Override
    public void saveBulk(List<ReviewDocument> reviews) {
        List<IndexQuery> queries = reviews.stream()
                .map(review -> new IndexQueryBuilder()
                        .withObject(review)
                        .build())
                .collect(Collectors.toList());
        elasticsearchOperations.bulkIndex(queries, ReviewDocument.class);
    }
}
