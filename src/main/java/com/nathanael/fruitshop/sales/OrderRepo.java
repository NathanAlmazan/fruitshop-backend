package com.nathanael.fruitshop.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {

    @Query("SELECT EXTRACT(year FROM s.timestamp) AS reportYear, EXTRACT(month FROM s.timestamp) AS reportMonth, EXTRACT(day FROM s.timestamp) AS reportDate, SUM(s.totalAmount) AS totalSales " +
            "FROM Orders AS s GROUP BY EXTRACT(year FROM s.timestamp), EXTRACT(month FROM s.timestamp), EXTRACT(day FROM s.timestamp) " +
            "ORDER BY EXTRACT(year FROM s.timestamp) DESC, EXTRACT(month FROM s.timestamp) DESC, EXTRACT(day FROM s.timestamp) DESC")
    List<DailySalesCount> getDailySalesCount();
}
