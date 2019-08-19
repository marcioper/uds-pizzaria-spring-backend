package com.udspizzaria.springcloudmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udspizzaria.springcloudmysql.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {} 
