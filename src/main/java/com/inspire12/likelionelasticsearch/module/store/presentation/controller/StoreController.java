package com.inspire12.likelionelasticsearch.module.store.presentation.controller;

import com.inspire12.likelionelasticsearch.module.store.application.StoreService;
import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.request.StoreSearchRequest;
import com.inspire12.likelionelasticsearch.module.store.application.dto.response.StoreListResponse;
import com.inspire12.likelionelasticsearch.module.store.application.dto.response.StoreResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stores")
@RestController
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/save")
    public StoreResponse save(@RequestBody StoreRequest request) {
        return storeService.save(request);
    }


    @PostMapping("/search")
    public StoreResponse search(@RequestBody StoreSearchRequest request) {
        return storeService.search(request);
    }

    @PostMapping("/recommend")
    public StoreListResponse recommend(@RequestBody StoreRequest request) {
        return storeService.recommendStore(request);
    }

}
