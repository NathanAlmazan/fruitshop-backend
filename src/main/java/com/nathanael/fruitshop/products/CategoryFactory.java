package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.ModelFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryFactory implements ModelFactory<Category, CategoryDto> {
    private final ProductFactory productFactory;

    public CategoryFactory(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    @Override
    public Category requestToEntity(CategoryDto request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setCategoryIcon(request.getCategoryIcon());
        category.setRawMaterial(request.getRawMaterial());

        return category;
    }

    @Override
    public CategoryDto entityToResponse(Category entity, List<String> additionalFields) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(entity.getCategoryId());
        categoryDto.setCategoryName(entity.getCategoryName());
        categoryDto.setCategoryIcon(entity.getCategoryIcon());
        categoryDto.setRawMaterial(entity.getRawMaterial());

        if (additionalFields != null) {
            if (additionalFields.remove("products")) {
                categoryDto.setCategoryProducts(productFactory.entityListToResponse(entity.getCategoryProducts(), additionalFields));
                additionalFields.add("products");
            }
        }

        return categoryDto;
    }

    public Category requestToUpdatedEntity(CategoryDto request, Category entity) {
        entity.setCategoryName(request.getCategoryName());
        entity.setCategoryIcon(request.getCategoryIcon());
        entity.setRawMaterial(request.getRawMaterial());

        return entity;
    }
}
