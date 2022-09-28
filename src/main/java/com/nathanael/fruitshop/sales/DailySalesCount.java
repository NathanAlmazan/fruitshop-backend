package com.nathanael.fruitshop.sales;

public interface DailySalesCount {
    Integer getReportYear();
    Integer getReportMonth();
    Integer getReportDate();
    Double getTotalSales();
}
