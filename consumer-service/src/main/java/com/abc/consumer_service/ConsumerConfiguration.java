package com.abc.consumer_service;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.abc.consumer_service.model.Order;

@Configuration
public class ConsumerConfiguration {

	@Bean
	ConsumerFactory<String, Order> orderConsumerFactory() {
		JsonDeserializer<Order> jsonDeserializer=new JsonDeserializer<>(Order.class,false);
		jsonDeserializer.addTrustedPackages("*");
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "order-id");		
		return new DefaultKafkaConsumerFactory<>(
				configProps, 
				new StringDeserializer(),
				jsonDeserializer);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, Order> orderKafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(orderConsumerFactory());
		return factory;
	}

}
