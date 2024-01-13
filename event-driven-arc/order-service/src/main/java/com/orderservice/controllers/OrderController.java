package com.orderservice.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basedomains.dto.Order;
import com.basedomains.dto.OrderEvent;
import com.orderservice.kafka_producer.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	private static final Logger log=LoggerFactory.getLogger(OrderController.class);
	

	private OrderProducer producer;


	public OrderController(OrderProducer producer) {
	
		this.producer = producer;
	}

	@PostMapping("/orders")
	public ResponseEntity placeOrders(@RequestBody Order order) {
	
	order.setOrderId(UUID.randomUUID().toString());
	
	OrderEvent event=new OrderEvent();
	event.setMessage("Order is in pending state");
	event.setStatus("PEND");
	event.setOrder(order);
	producer.sendMessage(event);
    return ResponseEntity.status(HttpStatus.CREATED).body("Order placed sucessfully");
		
	}
}
