package com.nathanael.fruitshop.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemsStatistics {
    private IngredientsDto item;
    private Long itemCount;
}
