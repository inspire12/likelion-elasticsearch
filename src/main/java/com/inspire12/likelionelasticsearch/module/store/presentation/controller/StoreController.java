package com.inspire12.likelionelasticsearch.module.store.presentation.controller;

import com.inspire12.likelionelasticsearch.module.store.application.StoreService;
import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreSearchRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.response.StoreResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stores")
@RestController
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/save")
    public StoreResponse search(@RequestBody StoreRequest request) {
        return storeService.save(request);
    }


    @GetMapping("/search")
    public StoreResponse search(@RequestBody StoreSearchRequest request) {
        return storeService.search(request);
    }

    @GetMapping("/recommend")
    public StoreResponse recommend(@RequestBody StoreRequest request) {
        return storeService.recommendStore(request);
    }

}
