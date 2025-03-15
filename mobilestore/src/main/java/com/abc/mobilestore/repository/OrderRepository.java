package com.abc.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.mobilestore.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
