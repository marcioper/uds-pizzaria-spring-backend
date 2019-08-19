package com.udspizzaria.springcloudmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udspizzaria.springcloudmysql.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {} 
