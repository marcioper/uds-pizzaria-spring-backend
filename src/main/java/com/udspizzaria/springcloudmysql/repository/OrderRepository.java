package com.udspizzaria.springcloudmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udspizzaria.springcloudmysql.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {} 
