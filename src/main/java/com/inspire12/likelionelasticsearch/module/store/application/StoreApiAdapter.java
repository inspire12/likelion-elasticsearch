package com.inspire12.likelionelasticsearch.module.store.application;



import com.inspire12.likelionelasticsearch.module.order.application.port.out.StoreStatusPort;
import org.springframework.stereotype.Component;

@Component
public class StoreApiAdapter implements StoreStatusPort {


    @Override
    public Boolean getStoreOpenStatus(Long storeId) {
        return true;
    }

    @Override
    public Boolean getStoreOpenStatusByOrderNumber(String orderNumber) {
        return true;
    }
}
