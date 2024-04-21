package com.bfu.orderservice.service.OrderProduct;

import com.bfu.orderservice.controller.payload.ArrayOfSimplifiedProduct;
import com.bfu.orderservice.controller.payload.SimplifiedProductResponse;
import com.bfu.orderservice.entity.Order;

import java.util.List;

public interface OrderProductService {
    void addOrderProduct(ArrayOfSimplifiedProduct product, Order order);

    List<SimplifiedProductResponse> getOrderProducts(String orderId);

    void deleteOrderProduct(String orderId);
}
