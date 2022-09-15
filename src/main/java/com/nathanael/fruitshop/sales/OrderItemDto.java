package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.products.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {

    private Long orderId;

    @NotNull(message = "Product code is required.")
    @Length(min = 1, max = 3)
    private String productCode;

    @NotNull(message = "Unit code is required.")
    @Length(min = 1, max = 3)
    private String unitCode;

    @NotNull(message = "Item quantity is required.")
    private Integer quantity;

    private ProductDto product;

}
