package com.inspire12.likelionelasticsearch.module.store.application;

import com.inspire12.likelionelasticsearch.module.store.domain.Store;

import java.util.List;

public interface StoreRepository {

    Store save(Store store);

    List<Store> recommend(Store store);
}
