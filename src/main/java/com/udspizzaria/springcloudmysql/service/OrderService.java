package com.udspizzaria.springcloudmysql.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

@Service
public class OrderService {

	private OrderRepository repository;
	private UserRepository userRepository;
	private ProductRepository productRepository;
	private SizeRepository sizeRepository;
	private FlavorRepository flavorRepository;
	private AdditionalRepository additionalRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, UserRepository userRepository,
			ProductRepository productRepository, SizeRepository sizeRepository, FlavorRepository flavorRepository,
			AdditionalRepository additionalRepository) {
		this.repository = orderRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.sizeRepository = sizeRepository;
		this.flavorRepository = flavorRepository;
		this.additionalRepository = additionalRepository;
	}

	public List<Order> findAll() {
		return repository.findAll();
	}

	public ResponseEntity<Order> findById(long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	public Order create(Order order) throws Exception {
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

	public ResponseEntity<Order> update(long id, Order order) {
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

	public ResponseEntity<?> delete(long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
