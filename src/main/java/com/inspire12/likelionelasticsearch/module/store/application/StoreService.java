package com.inspire12.likelionelasticsearch.module.store.application;

import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreSearchRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.response.StoreListResponse;
import com.inspire12.likelionelasticsearch.module.store.application.dto.response.StoreResponse;
import com.inspire12.likelionelasticsearch.module.store.domain.Store;
import com.inspire12.likelionelasticsearch.module.store.support.StoreMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreListResponse recommendStore(StoreRequest request) {
        List<Store> recommend = storeRepository.recommend(StoreMapper.fromRequest(request));

        return new StoreListResponse(recommend.stream().map(StoreMapper::toResponse).toList());
    }

    public StoreResponse search(StoreSearchRequest request) {
        return null;
    }

    public StoreResponse save(StoreRequest request) {
        return StoreMapper.toResponse(storeRepository.save(StoreMapper.fromRequest(request)));
    }
}
