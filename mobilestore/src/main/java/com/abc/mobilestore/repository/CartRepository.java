package com.abc.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.mobilestore.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{

}
