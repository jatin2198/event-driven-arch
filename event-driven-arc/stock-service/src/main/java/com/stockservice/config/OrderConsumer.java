package com.stockservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.basedomains.dto.OrderEvent;





@Service
public class OrderConsumer {

	private static final Logger log=LoggerFactory.getLogger(OrderConsumer.class);
	
	
	
	@KafkaListener(topics ="${spring.kafka.template.default-topic}",groupId ="${spring.kafka.consumer.group-id}")
	public void consume(OrderEvent event) {
		log.info(String.format("message consumed=> %s", event.toString()));
	}
	
	
}
