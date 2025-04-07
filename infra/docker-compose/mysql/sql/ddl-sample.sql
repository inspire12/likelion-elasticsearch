CREATE DATABASE IF NOT EXISTS `likelion`;
use `likelion`
CREATE
DATABASE IF NOT EXISTS `likelion`;
USE
`likelion`;

SET NAMES utf8mb4;

-- CREATE TABLE IF NOT EXIST
DROP TABLE IF EXISTS `orders`;
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    order_number VARCHAR(36) NOT NULL UNIQUE,
    order_status VARCHAR(20) NOT NULL,
    total_amount INT NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    delivery_address VARCHAR(255) NOT NULL,
    request_comment VARCHAR(255),
    ordered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO orders
(customer_id, order_number, order_status, total_amount, payment_method, delivery_address, request_comment)
VALUES
    (1, UUID(), 'ORDERED', 25000, 'CREDIT_CARD', '서울특별시 강남구 테헤란로 123', '빨리 와주세요'),
    (2, UUID(), 'PREPARING', 15000, 'KAKAO_PAY', '서울특별시 마포구 서교동 456', null);

SELECT * FROM orders;

SELECT * FROM orders WHERE order_status = 'ORDERED';
