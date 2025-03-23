package com.inspire12.likelionelasticsearch.module.store.domain.adapter;



import com.inspire12.likelionelasticsearch.module.order.application.port.out.StoreStatusPort;
import org.springframework.stereotype.Component;

@Component
public class StoreApiAdapter implements StoreStatusPort {


    @Override
    public Boolean getStoreOpenStatus(Long storeId) {
        return true;
    }
}
