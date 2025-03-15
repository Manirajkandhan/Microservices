package com.abc.producer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.producer_service.model.Order;
import com.abc.producer_service.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
		
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/save")
	public ResponseEntity<Order> placeOrder(@RequestBody Order order){
		orderService.sendOrder(order);
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);
	}
}
