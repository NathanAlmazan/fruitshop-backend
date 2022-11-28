package com.ntea.fruitshop.products;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UnitPricesDto {
    @NotNull(message = "Unit type is required.")
    @Length(min = 1, max = 3, message = "Unit type should be 1 to 3 characters long only.")
    private String unitType;

    @NotNull(message = "Unit price is required.")
    private Double unitPrice;

    private String productCode;
}
