package com.inspire12.likelionelasticsearch.module.order.presentation.controller;

import com.inspire12.likelionelasticsearch.module.order.application.dto.request.OrderRequest;
import com.inspire12.likelionelasticsearch.module.order.application.dto.response.OrderListResponse;
import com.inspire12.likelionelasticsearch.module.order.application.dto.response.OrderResponse;
import com.inspire12.likelionelasticsearch.module.order.application.service.OrderService;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/order")
@RestController
public class OrderController {

    private final OrderService orderUsecase;

    public OrderController(OrderService orderUsecase) {
        this.orderUsecase = orderUsecase;
    }

    @GetMapping
    public OrderListResponse getOrders(@PageableDefault(page = 0, sort = {"id.desc"}) Pageable pageable) {
        return orderUsecase.getOrders(pageable);
    }

    @GetMapping("/id")
    public OrderResponse getOrderById(@RequestParam Long orderId) {
        return orderUsecase.getOrderById(orderId);
    }


    /**
     * Handles POST requests for creating an order.
     *
     * @param orderRequest the request body containing order details
     * @return a string indicating the outcome of the order process
     */
    @PostMapping
    public OrderResponse saveOrder(@RequestBody OrderRequest orderRequest) {
        // Process the order request using the order service

        return orderUsecase.saveOrder(orderRequest);
    }
}