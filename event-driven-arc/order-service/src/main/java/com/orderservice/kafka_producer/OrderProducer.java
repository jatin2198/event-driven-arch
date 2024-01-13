package com.orderservice.kafka_producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.basedomains.dto.OrderEvent;

import lombok.AllArgsConstructor;

@Service

public class OrderProducer {
	
	private static final Logger log=LoggerFactory.getLogger(OrderProducer.class);
	@Autowired
	private NewTopic newTopic;

	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	public OrderProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {

		this.newTopic = newTopic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(OrderEvent event) {
		
		log.info(String.format("Order event=> %s", event.toString()));
		
		Message<OrderEvent> message=MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC,newTopic.name()).build();
		kafkaTemplate.send(message);
	}
	
	
	
	
}
