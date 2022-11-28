package com.ntea.fruitshop.products;

import com.ntea.fruitshop.global.ModelFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFactory implements ModelFactory<Product, ProductDto> {
    private final UnitPricesFactory unitPricesFactory;
    private final CategoryServices categoryServices;

    public ProductFactory(@Lazy UnitPricesFactory unitPricesFactory, @Lazy CategoryServices categoryServices) {
        this.unitPricesFactory = unitPricesFactory;
        this.categoryServices = categoryServices;
    }

    @Override
    public Product requestToEntity(ProductDto request) {
        Product product = new Product();
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setDiscountedPrice(request.getDiscountedPrice());
        product.setProductImage(request.getProductImage());
        product.setIsActive(request.getIsActive());
        product.setProductCategory(categoryServices.getById(request.getCategoryId()));

        return product;
    }

    @Override
    public ProductDto entityToResponse(Product entity, List<String> additionalFields) {
        ProductDto productDto = new ProductDto();
        productDto.setProductCode(entity.getProductCode());
        productDto.setProductName(entity.getProductName());
        productDto.setDiscountedPrice(entity.getDiscountedPrice());
        productDto.setProductImage(entity.getProductImage());
        productDto.setIsActive(entity.getIsActive());
        if (entity.getProductCategory() != null) productDto.setCategoryId(entity.getProductCategory().getCategoryId());

        if (additionalFields != null) {
            if (additionalFields.remove("prices")) {
                productDto.setUnitPrices(unitPricesFactory.entityListToResponse(entity.getUnitPrices(), additionalFields));
                additionalFields.add("prices");
            }

            if (additionalFields.remove("category")) {
                if (entity.getProductCategory() != null)
                    productDto.setProductCategory(categoryServices.getDtoById(entity.getProductCategory().getCategoryId(), additionalFields));
                additionalFields.add("category");
            }
        }

        return productDto;
    }

    public Product requestToUpdatedEntity(ProductDto request, Product entity) {
        entity.setProductName(request.getProductName());
        entity.setDiscountedPrice(request.getDiscountedPrice());
        entity.setProductImage(request.getProductImage());
        entity.setIsActive(request.getIsActive());
        entity.setProductCategory(categoryServices.getById(request.getCategoryId()));

        return entity;
    }
}
