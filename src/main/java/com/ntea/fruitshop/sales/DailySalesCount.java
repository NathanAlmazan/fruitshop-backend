package com.ntea.fruitshop.sales;

public interface DailySalesCount {
    Integer getReportYear();
    Integer getReportMonth();
    Integer getReportDate();
    Double getTotalSales();
}
