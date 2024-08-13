package com.webshop.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.main.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
