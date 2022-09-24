package com.nathanael.fruitshop.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {
}
