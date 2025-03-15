package com.abc.producer_service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {
	
	@Bean
	NewTopic createPaymentTopic() {
		return TopicBuilder.name("payment-topic").build();
	}
	@Bean
	NewTopic createOrderTopic() {
		return TopicBuilder.name("order-topic").build();
}
}
