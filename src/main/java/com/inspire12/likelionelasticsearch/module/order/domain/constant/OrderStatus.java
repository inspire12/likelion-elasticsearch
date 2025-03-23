package com.inspire12.likelionelasticsearch.module.order.domain.constant;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum OrderStatus {
    @JsonEnumDefaultValue PENDING_PAYMENT,
    SUCCESS_PAYMENT,
    CANCEL_PAYMENT,
    FAIL_PAYMENT,
}
