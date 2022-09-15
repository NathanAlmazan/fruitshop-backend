package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.ModelFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitPricesFactory implements ModelFactory<UnitPrices, UnitPricesDto> {
    private final ProductServices productServices;
    private final UnitMeasureServices unitMeasureServices;

    public UnitPricesFactory(@Lazy ProductServices productServices, @Lazy UnitMeasureServices unitMeasureServices) {
        this.productServices = productServices;
        this.unitMeasureServices = unitMeasureServices;
    }

    @Override
    public UnitPrices requestToEntity(UnitPricesDto request) {
        UnitPrices unitPrices = new UnitPrices();
        unitPrices.setUnitPrice(request.getUnitPrice());
        unitPrices.setUnitPriceKey(new UnitPriceKey(request.getUnitType(), request.getProductCode()));
        unitPrices.setProduct(productServices.getById(request.getProductCode()));
        unitPrices.setUnitType(unitMeasureServices.getById(request.getUnitType()));

        return unitPrices;
    }

    @Override
    public UnitPricesDto entityToResponse(UnitPrices entity, List<String> additionalFields) {
        UnitPricesDto unitPrices = new UnitPricesDto();
        unitPrices.setUnitPrice(entity.getUnitPrice());
        unitPrices.setUnitType(entity.getUnitPriceKey().getUnitCode());
        unitPrices.setProductCode(entity.getUnitPriceKey().getProductCode());

        return unitPrices;
    }
}
