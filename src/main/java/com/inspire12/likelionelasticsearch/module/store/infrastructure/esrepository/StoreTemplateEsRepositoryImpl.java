package com.inspire12.likelionelasticsearch.module.store.infrastructure.esrepository;

// Elasticsearch Core (org.elasticsearch.*)

import co.elastic.clients.json.JsonData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreSearchRequest;
import com.inspire12.likelionelasticsearch.module.store.infrastructure.document.StoreDocument;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class StoreTemplateEsRepositoryImpl implements StoreTemplateEsRepository {
    private final ElasticsearchOperations elasticsearchOperations;
    private final String indexNamePrefix = "store-";
    private final ObjectMapper objectMapper;

    public StoreTemplateEsRepositoryImpl(ElasticsearchOperations elasticsearchOperations, ObjectMapper objectMapper) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.objectMapper = objectMapper;
    }


    //    searchByNamedQuery
    @Override
    public SearchHits<StoreDocument> search(StoreSearchRequest request) {
        // TODO content(store) 에서 확인, 다양한 패턴으로 검색 해보기
        String wildcardIndex = indexNamePrefix + "2025-*";

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> b
//                                .must(m -> m.match(mt -> mt.field("content").query(request.getKeyword())))
//                                .filter(f -> f.range(r -> r.number(r1 -> r1.field("rating").gte(4.0))))
//                                .filter(f -> f.range(r -> r.date(r2 -> r2.field("date").gte("now-1M/M"))))
                        ))

//                     .withSort(Sort.by(Sort.Direction.DESC, "rating"))
                .build();

//        nativeQuery.setPageable(PageRequest.of(request.getPage(), request.getSize()));

        SearchHits<StoreDocument> hits = elasticsearchOperations.search(nativeQuery, StoreDocument.class, IndexCoordinates.of(wildcardIndex));
        return hits;
    }

    @Override
    public void saveBulk(List<StoreDocument> stores) {
        if (stores.isEmpty()) {
            return;
        }
        String indexName = indexNamePrefix + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

        List<IndexQuery> queries = stores.stream()
                .map(review -> new IndexQueryBuilder()
                        .withIndex(indexName)
                        .withObject(review)
                        .build())
                .collect(Collectors.toList());
        elasticsearchOperations.bulkIndex(queries, StoreDocument.class);
    }

    @Override
    public StoreDocument saveWithIndex(StoreDocument storeDocument) {
        // 특수문자 제거

        // index pattern 날짜별
        String indexName = indexNamePrefix + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        elasticsearchOperations.save(storeDocument, IndexCoordinates.of(indexName));

        return storeDocument;
    }

    @Override
    public List<StoreDocument> searchSimilarStores(StoreDocument storeDocument, int topK) {
//        ScriptScoreQuery scriptScoreQuery = QueryBuilders.scriptScore()
//                .script(Script.of(s -> s
//
//                ))
//                .build();

        float[] vector = storeDocument.getVector();
        List<Float> queryVector = new ArrayList<>();
        for (float f : vector) {
            queryVector.add(f);
        }
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(q -> q.scriptScore(ss -> ss
                        .query(query -> query.matchAll(m -> m))
                        .script(script -> {

                                    return script
                                            .source("cosineSimilarity(params.vector, 'vector') + 1.0")
                                            .params(Map.of("vector", JsonData.of(queryVector)));

                                }
                        )
                ))
                .withPageable(PageRequest.of(0, topK))
                .build();

        return elasticsearchOperations.search(searchQuery, StoreDocument.class)
                .map(SearchHit::getContent)
                .toList();
    }

}
