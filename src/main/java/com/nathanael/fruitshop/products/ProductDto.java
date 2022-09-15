package com.nathanael.fruitshop.products;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductDto {

    @NotNull(message = "Product code is required.")
    @Length(min = 1, max = 3, message = "Product code should be 1 to 3 characters long only.")
    private String productCode;

    @NotNull(message = "Product name is required.")
    @Length(min = 1, max = 30, message = "Product name should be 1 to 30 characters long only.")
    private String productName;

    @NotNull(message = "Product unit prices is required.")
    @Valid
    private List<UnitPricesDto> unitPrices;

    private Double discountedPrice = 0.00;

    @NotNull(message = "Product image is required.")
    @Length(min = 1, max = 255, message = "Product image link should be 1 to 255 characters long only.")
    private String productImage;

    private Boolean isActive = true;

    @NotNull(message = "Product category is required.")
    private Long categoryId;

    private CategoryDto productCategory;
}
