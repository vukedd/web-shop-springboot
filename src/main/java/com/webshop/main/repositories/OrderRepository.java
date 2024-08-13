package com.webshop.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.main.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
