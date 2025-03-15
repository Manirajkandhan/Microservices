package com.abc.consumer_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.abc.consumer_service.model.Order;

@Service
public class NotificationService {

	@KafkaListener(topics = "order-topic", groupId = "order-id",containerFactory = "orderKafkaListenerContainerFactory")
	
	public void sendNotification(Order order) {
		
		System.out.println("Hello "+order.getCustomerName()+" Your Order is placed. Order Amount : "+order.getOrderAmount());;
	}
}