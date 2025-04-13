package com.inspire12.likelionelasticsearch.module.store.support;

import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.response.StoreResponse;
import com.inspire12.likelionelasticsearch.module.store.domain.Store;
import com.inspire12.likelionelasticsearch.module.store.infrastructure.document.StoreDocument;

public class StoreMapper {

    public static Store fromRequest(StoreRequest request) {
        return Store.builder()
                .name(request.getName())
                .description(request.getDescription())
                .menus(request.getMenus())
                .category(request.getCategory())
                .rating(request.getRating())
                .location(request.getLocation())
                .build();
    }

    public static Store fromDocument(StoreDocument storeDocument) {
        return Store.builder()
                .name(storeDocument.getName())
                .description(storeDocument.getDescription())
                .menus(storeDocument.getMenus())
                .category(storeDocument.getCategory())
                .rating(storeDocument.getRating())
                .location(storeDocument.getLocation())
                .build();
    }


    public static StoreDocument toDocument(Store store) {
        return StoreDocument.builder()
                .name(store.getName())
                .description(store.getDescription())
                .menus(store.getMenus())
                .category(store.getCategory())
                .rating(store.getRating())
                .location(store.getLocation())
                .build();
    }


    public static StoreResponse toResponse(Store store) {
        return StoreResponse.builder()
                .name(store.getName())
                .description(store.getDescription())
                .menus(store.getMenus())
                .category(store.getCategory())
                .rating(store.getRating())
                .location(store.getLocation())
                .build();
    }
}
