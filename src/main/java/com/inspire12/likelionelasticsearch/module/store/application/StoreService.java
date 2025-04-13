package com.inspire12.likelionelasticsearch.module.store.application;

import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreSearchRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.response.StoreResponse;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreResponse recommendStore(StoreRequest request) {
        return null;
    }

    public StoreResponse search(StoreSearchRequest request) {
        return null;
    }

    public StoreResponse save(StoreRequest request) {
        return null;
    }
}
