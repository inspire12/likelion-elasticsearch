package com.inspire12.likelionelasticsearch.module.store.infrastructure.esrepository;


import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreSearchRequest;
import com.inspire12.likelionelasticsearch.module.store.infrastructure.document.StoreDocument;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StoreTemplateEsRepository {
//    List<String> autocompleteSentiment(String input);


    //        CriteriaQuery query = new CriteriaQuery(criteria);
    //        query.setPageable(PageRequest.of(request.getPage(), request.getSize()));
    //

    //    searchByNamedQuery
    SearchHits<StoreDocument> search(StoreSearchRequest request);

    void saveBulk(List<StoreDocument> stores);

    StoreDocument saveWithIndex(StoreDocument storeDocument);

    List<StoreDocument> searchSimilarStores(StoreDocument store, int topK);
}