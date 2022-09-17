package com.nathanael.fruitshop.inventory;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class IngredientsDto {

    private Long itemId;

    @NotNull(message = "Item name is required.")
    @Length(min = 1, max = 30, message = "Item name is between 1 to 30 characters.")
    private String itemName;

    @NotNull(message = "Item brand is required.")
    @Length(min = 1, max = 20, message = "Item brand is between 1 to 20 characters.")
    private String brandName;

    @NotNull(message = "Item units is required.")
    @Length(min = 1, max = 3, message = "Item units is between 1 to 3 characters.")
    private String units;

    @NotNull(message = "Item stock count is required.")
    private Integer inStock;

    @NotNull(message = "Item price is required.")
    private Double unitPrice;

    private LocalDateTime lastUpdated;

}
