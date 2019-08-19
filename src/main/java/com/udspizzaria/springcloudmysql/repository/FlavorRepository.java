package com.udspizzaria.springcloudmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udspizzaria.springcloudmysql.model.Flavor;

@Repository
public interface FlavorRepository extends JpaRepository<Flavor, Long> {} 
