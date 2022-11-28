package com.ntea.fruitshop.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PurchasedItemsDto {

    private Long purchaseId;

    @NotNull(message = "Ingredient info is required.")
    private Long itemId;

    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    private IngredientsDto ingredientsDto;
}
