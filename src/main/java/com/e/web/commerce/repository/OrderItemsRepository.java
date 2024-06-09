package com.e.web.commerce.repository;

import com.e.web.commerce.Entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
