package com.inspire12.likelionelasticsearch.module.store.infrastructure.adpater;

import com.inspire12.likelionelasticsearch.module.store.application.StoreRepository;
import com.inspire12.likelionelasticsearch.module.store.domain.Store;
import com.inspire12.likelionelasticsearch.module.store.infrastructure.document.StoreDocument;
import com.inspire12.likelionelasticsearch.module.store.infrastructure.esrepository.StoreEsRepository;
import com.inspire12.likelionelasticsearch.module.store.support.StoreMapper;
import org.springframework.ai.embedding.Embedding;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreAdapter implements StoreRepository {

    private final StoreEsRepository storeEsRepository;
    private final EmbeddingStoreService embeddingStoreService;

    public StoreAdapter(StoreEsRepository storeEsRepository, EmbeddingStoreService embeddingStoreService) {
        this.storeEsRepository = storeEsRepository;
        this.embeddingStoreService = embeddingStoreService;
    }


    @Override
    public Store save(Store store) {
        StoreDocument document = StoreMapper.toDocument(store);
        Embedding embedding = embeddingStoreService.getEmbedding(document);
        document.setVectorFromOpenAi(embedding.getOutput());
        return StoreMapper.fromDocument(storeEsRepository.save(document));
    }

    @Override
    public List<Store> recommend(Store store) {
        StoreDocument document = StoreMapper.toDocument(store);
        Embedding embedding = embeddingStoreService.getEmbedding(document);
        document.setVectorFromOpenAi(embedding.getOutput());
        return storeEsRepository.searchSimilarStores(document, 5)
                .stream().map(StoreMapper::fromDocument).toList();

    }
}
