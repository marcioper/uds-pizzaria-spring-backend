package com.udspizzaria.springcloudmysql.controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udspizzaria.springcloudmysql.exception.AppException;
import com.udspizzaria.springcloudmysql.model.Additional;
import com.udspizzaria.springcloudmysql.model.Flavor;
import com.udspizzaria.springcloudmysql.model.Order;
import com.udspizzaria.springcloudmysql.model.Product;
import com.udspizzaria.springcloudmysql.model.Size;
import com.udspizzaria.springcloudmysql.model.User;
import com.udspizzaria.springcloudmysql.repository.AdditionalRepository;
import com.udspizzaria.springcloudmysql.repository.FlavorRepository;
import com.udspizzaria.springcloudmysql.repository.OrderRepository;
import com.udspizzaria.springcloudmysql.repository.ProductRepository;
import com.udspizzaria.springcloudmysql.repository.SizeRepository;
import com.udspizzaria.springcloudmysql.repository.UserRepository;

@RestController
@RequestMapping({ "/api/v1/orders" })
public class OrderController {

	private OrderRepository repository;
	private UserRepository userRepository;
	private ProductRepository productRepository;
	private SizeRepository sizeRepository;
	private FlavorRepository flavorRepository;
	private AdditionalRepository additionalRepository;

	OrderController(OrderRepository orderRepository,
			UserRepository userRepository, ProductRepository productRepository, SizeRepository sizeRepository,
			FlavorRepository flavorRepository, AdditionalRepository additionalRepository) {
		this.repository = orderRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.sizeRepository = sizeRepository;
		this.flavorRepository = flavorRepository;
		this.additionalRepository = additionalRepository;
	}

	@GetMapping
	public List<Order> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Order> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Order create(@RequestBody Order order) throws Exception {
		double priceTotal = 0;
		int preparationTime = 0;

		try {
			User user = userRepository.findById(order.getUser().getId()).get();
			Product product = productRepository.findById(order.getProduct().getId()).get();
			Size size = sizeRepository.findById(order.getSize().getId()).get();
			priceTotal += size.getPrice().doubleValue();
			preparationTime += size.getPreparation_time();
			Flavor flavor = flavorRepository.findById(order.getFlavor().getId()).get();
			preparationTime += flavor.getPreparation_time();

			Set<Additional> listAdditional = order.getAdditionals();
			Set<Additional> listAdditionalFull = new HashSet<Additional>();
			Additional el = null;
			for (Additional additional : listAdditional) {
				el = additionalRepository.findById(additional.getId()).get();
				listAdditionalFull.add(el);
				priceTotal += el.getPrice().doubleValue();
				preparationTime += el.getPreparation_time();
			}
			order.setUser(user);
			order.setProduct(product);
			order.setSize(size);
			order.setFlavor(flavor);
			order.setAdditionals(listAdditionalFull);
			order.setPrice_total(new BigDecimal(priceTotal));
			order.setPreparation_time(preparationTime);
			
			return repository.save(order);
		} catch (Exception e) {
			throw new AppException("Error: " + e);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> update(@PathVariable("id") long id, @RequestBody Order order) {
		return repository.findById(id).map(record -> {
			record.setUser(order.getUser());
			record.setSize(order.getSize());
			record.setFlavor(order.getFlavor());
			record.setPreparation_time(order.getPreparation_time());
			record.setPrice_total(order.getPrice_total());
			record.setAdditionals(order.getAdditionals());
			Order updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
