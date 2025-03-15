package com.abc.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.mobilestore.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Integer>{

}
