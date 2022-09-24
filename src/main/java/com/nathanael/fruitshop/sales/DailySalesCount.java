package com.nathanael.fruitshop.sales;

public interface DailySalesCount {
    Integer getReportMonth();
    Integer getReportDate();
    Double getTotalSales();
}
