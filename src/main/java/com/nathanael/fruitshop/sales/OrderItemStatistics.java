package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.products.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemStatistics {
    private ProductDto product;
    private Double percentage;
}
