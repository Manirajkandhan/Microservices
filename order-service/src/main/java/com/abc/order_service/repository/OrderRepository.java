package com.abc.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.order_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}