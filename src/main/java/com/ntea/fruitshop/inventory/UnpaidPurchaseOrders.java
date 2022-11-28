package com.ntea.fruitshop.inventory;

import java.time.LocalDateTime;

public interface UnpaidPurchaseOrders {
    Long getPurchaseId();
    LocalDateTime getOrderDueDate();
}
