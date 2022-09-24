package com.nathanael.fruitshop.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {

    @Query("SELECT EXTRACT(month FROM s.timestamp) AS reportMonth, EXTRACT(day FROM s.timestamp) AS reportDate, SUM(s.totalAmount) AS totalSales FROM Orders AS s GROUP BY EXTRACT(month FROM s.timestamp), EXTRACT(day FROM s.timestamp)")
    List<DailySalesCount> getDailySalesCount();
}
