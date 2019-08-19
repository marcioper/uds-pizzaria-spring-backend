package com.udspizzaria.springcloudmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udspizzaria.springcloudmysql.model.Additional;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long> {} 
