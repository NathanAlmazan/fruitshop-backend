package com.ntea.fruitshop.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {

    @Query("SELECT EXTRACT(year FROM s.purchaseDate) AS reportYear, EXTRACT(month FROM s.purchaseDate) AS reportMonth, EXTRACT(day FROM s.purchaseDate) AS reportDate, SUM(s.totalPrice) AS totalPurchase " +
            "FROM PurchaseOrder AS s GROUP BY EXTRACT(year FROM s.purchaseDate), EXTRACT(month FROM s.purchaseDate), EXTRACT(day FROM s.purchaseDate) " +
            "ORDER BY EXTRACT(year FROM s.purchaseDate) DESC, EXTRACT(month FROM s.purchaseDate) DESC, EXTRACT(day FROM s.purchaseDate)")
    List<PurchaseSummary> getPurchaseSummary();

    @Query("SELECT p.purchaseId AS purchaseId, p.dueDate AS orderDueDate FROM PurchaseOrder AS p WHERE p.paid = false")
    List<UnpaidPurchaseOrders> getUnpaidPurchaseOrders();
}
