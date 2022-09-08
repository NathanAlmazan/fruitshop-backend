package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.ModelFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFactory implements ModelFactory<Product, ProductDto> {
    private final UnitMeasureServices unitMeasureServices;
    private final CategoryServices categoryServices;

    public ProductFactory(
            @Lazy UnitMeasureServices unitMeasureServices,
            @Lazy CategoryServices categoryServices
    ) {
        this.unitMeasureServices = unitMeasureServices;
        this.categoryServices = categoryServices;
    }

    @Override
    public Product requestToEntity(ProductDto request) {
        Product product = new Product();
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setUnitType(unitMeasureServices.getById(request.getUnitTypeCode()));
        product.setUnitPrice(request.getUnitPrice());
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
        productDto.setUnitTypeCode(entity.getUnitType().getUnitCode());
        productDto.setUnitPrice(entity.getUnitPrice());
        productDto.setDiscountedPrice(entity.getDiscountedPrice());
        productDto.setProductImage(entity.getProductImage());
        productDto.setIsActive(entity.getIsActive());
        if (entity.getProductCategory() != null) productDto.setCategoryId(entity.getProductCategory().getCategoryId());

        if (additionalFields != null) {
            if (additionalFields.remove("units")) {
                productDto.setUnitType(unitMeasureServices.getDtoById(entity.getUnitType().getUnitCode(), additionalFields));
                additionalFields.add("units");
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
        entity.setUnitType(unitMeasureServices.getById(request.getUnitTypeCode()));
        entity.setUnitPrice(request.getUnitPrice());
        entity.setDiscountedPrice(request.getDiscountedPrice());
        entity.setProductImage(request.getProductImage());
        entity.setIsActive(request.getIsActive());
        entity.setProductCategory(categoryServices.getById(request.getCategoryId()));

        return entity;
    }
}
