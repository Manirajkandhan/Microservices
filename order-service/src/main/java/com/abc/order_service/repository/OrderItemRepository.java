package com.abc.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.order_service.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

}