package com.udspizzaria.springcloudmysql.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udspizzaria.springcloudmysql.model.Order;
import com.udspizzaria.springcloudmysql.service.OrderService;

@RestController
@RequestMapping({ "/api/v1/orders" })
public class OrderController {

	private OrderService service;

	OrderController(OrderService orderService) {
		this.service = orderService;
	}

	@GetMapping
	public List<Order> findAll() {
		return service.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Order> findById(@PathVariable long id) {
		return service.findById(id);
	}

	@PostMapping
	public Order create(@RequestBody Order order) throws Exception {
		return service.create(order);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> update(@PathVariable("id") long id, @RequestBody Order order) {
		return service.update(id, order);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return service.findById(id);
	}

}
