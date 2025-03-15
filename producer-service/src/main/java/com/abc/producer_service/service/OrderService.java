package com.abc.producer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.producer_service.model.Order;

@Service
public class OrderService {
	
	@Autowired
	private KafkaTemplate<String,Order> kafkaTemplate;

	public void sendOrder(Order order) {
		
		kafkaTemplate.send("order-topic", order);
	}
}
