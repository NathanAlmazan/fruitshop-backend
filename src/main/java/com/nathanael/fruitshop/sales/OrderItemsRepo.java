package com.nathanael.fruitshop.sales;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItems, OrderItemKey> {
}
