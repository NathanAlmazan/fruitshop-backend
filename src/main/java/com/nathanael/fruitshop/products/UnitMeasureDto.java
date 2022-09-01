package com.nathanael.fruitshop.products;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UnitMeasureDto {

    @NotNull(message = "Unit code is required.")
    @Length(min = 1, max = 3, message = "Unit code should be 1 to 3 characters long only.")
    private String unitCode;

    @NotNull(message = "Unit label is required.")
    @Length(min = 1, max = 15, message = "Unit label should be 1 to 15 characters long only.")
    private String unitLabel;
}
