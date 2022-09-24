package com.nathanael.fruitshop.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailySalesReport {
    private Integer reportMonth;
    private Integer reportDate;
    private Double totalSales;
}
