package com.example.shopbook.repositories;

import com.example.shopbook.models.Order;
import com.example.shopbook.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //Tìm các đơn hàng của 1 user nào đó
    List<OrderDetail> findByUserId(Long userId);
}
