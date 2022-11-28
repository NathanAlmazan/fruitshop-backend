package com.ntea.fruitshop.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseReport {
    private Integer reportYear;
    private Integer reportMonth;
    private Integer reportDate;
    private Double totalPurchase;
}
