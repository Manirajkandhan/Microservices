package com.abc.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.mobilestore.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

}
