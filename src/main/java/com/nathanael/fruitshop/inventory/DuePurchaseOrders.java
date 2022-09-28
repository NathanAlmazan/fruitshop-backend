package com.nathanael.fruitshop.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DuePurchaseOrders {
    private PurchaseOrderDto purchaseOrder;
    private LocalDateTime dueDate;
}
