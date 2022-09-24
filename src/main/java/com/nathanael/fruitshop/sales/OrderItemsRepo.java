package com.nathanael.fruitshop.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemsRepo extends JpaRepository<OrderItems, OrderItemKey> {

    @Query("SELECT p.orderItemKey.productCode AS productCode, COUNT(p.orderItemKey.productCode) AS productCount FROM OrderItems AS p GROUP BY p.orderItemKey.productCode")
    List<GroupByProduct> getGroupByProduct();

    @Query("SELECT COUNT(c.orderItemKey.productCode) AS entityCount FROM OrderItems AS c")
    EntityCount getOrderItemsCount();
}
