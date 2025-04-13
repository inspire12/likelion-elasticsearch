package com.inspire12.likelionelasticsearch.module.store.infrastructure.esrepository;

import com.inspire12.likelionelasticsearch.module.store.infrastructure.document.StoreDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreEsRepository extends ElasticsearchRepository<StoreDocument, String>, StoreTemplateEsRepository {
}
