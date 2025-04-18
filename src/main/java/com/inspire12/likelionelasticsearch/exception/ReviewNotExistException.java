package com.inspire12.likelionelasticsearch.exception;

import org.springframework.http.HttpStatus;

public class ReviewNotExistException extends RuntimeException {
//    private final ErrorCode errorCode; // 커스텀 에러 코드 (enum)
    private final String detail;       // 추가 상세 정보 (선택적)
    private final HttpStatus status;

    public ReviewNotExistException() {
        this.detail = "Order 가 존재하지 않습니다.";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public ReviewNotExistException(String detail) {
        this.detail = detail;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
