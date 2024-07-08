package com.example.shopbook.services;

import com.example.shopbook.dtos.OrderDTO;
import com.example.shopbook.exceptions.DataNotFoundException;
import com.example.shopbook.responses.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;
    OrderResponse getOrder(Long id);
    OrderResponse updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
    List<OrderResponse> getAllOrders(Long userId);
}
