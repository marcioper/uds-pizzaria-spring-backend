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

import com.udspizzaria.springcloudmysql.model.User;
import com.udspizzaria.springcloudmysql.service.UserService;

@RestController
@RequestMapping({ "/api/v1/users" })
public class UserController {

	private UserService service;

	UserController(UserService userService) {
		this.service = userService;
	}

	@GetMapping
	public List<User> findAll() {
		return service.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<User> findById(@PathVariable long id) {
		return service.findById(id);
	}

	@PostMapping
	public User create(@RequestBody User user) {
		return service.create(user);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User user) {
		return service.update(id, user);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return service.delete(id);
	}
}
