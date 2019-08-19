package com.udspizzaria.springcloudmysql.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "ordered")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "user_id")
   private User user;
   
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "product_id")
   private Product product;
   
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "size_id")
   private Size size;
   
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "flavor_id")
   private Flavor flavor;
   
   @ManyToMany
   @JoinTable(
     name = "ordered_additional", 
     joinColumns = @JoinColumn(name = "order_id"), 
     inverseJoinColumns = @JoinColumn(name = "additional_id"))
   Set<Additional> additionals;
   
   private BigDecimal price_total;
   private int preparation_time;
   
   @CreatedDate
   @Column(nullable = false, updatable = false)
   private Instant createdAt;
}
