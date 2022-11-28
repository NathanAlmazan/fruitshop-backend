package com.ntea.fruitshop.inventory;

public interface PurchaseSummary {
    Integer getReportYear();
    Integer getReportMonth();
    Integer getReportDate();
    Double getTotalPurchase();
}
