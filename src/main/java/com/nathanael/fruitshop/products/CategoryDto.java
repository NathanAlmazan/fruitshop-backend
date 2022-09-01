package com.nathanael.fruitshop.products;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryDto {

    private Long categoryId;

    @NotNull(message = "Category name is required.")
    @Length(min = 1, max = 15, message = "Category name should be 1 to 15 characters long only.")
    private String categoryName;

    @NotNull(message = "Category icon is required.")
    private String categoryIcon;

    private Boolean rawMaterial = false;

    private List<ProductDto> categoryProducts;
}
