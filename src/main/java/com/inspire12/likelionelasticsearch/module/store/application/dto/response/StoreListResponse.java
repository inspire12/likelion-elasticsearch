package com.inspire12.likelionelasticsearch.module.store.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreListResponse {
    List<StoreResponse> storeResponseList;
}
