package com.ntea.fruitshop.products;

import com.ntea.fruitshop.global.ModelFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitMeasureFactory implements ModelFactory<UnitMeasure, UnitMeasureDto> {

    @Override
    public UnitMeasure requestToEntity(UnitMeasureDto request) {
        UnitMeasure unitMeasure = new UnitMeasure();
        unitMeasure.setUnitCode(request.getUnitCode());
        unitMeasure.setUnitLabel(request.getUnitLabel());

        return unitMeasure;
    }

    @Override
    public UnitMeasureDto entityToResponse(UnitMeasure entity, List<String> additionalFields) {
        UnitMeasureDto unitMeasureDto = new UnitMeasureDto();
        unitMeasureDto.setUnitCode(entity.getUnitCode());
        unitMeasureDto.setUnitLabel(entity.getUnitLabel());

        return unitMeasureDto;
    }

    public UnitMeasure requestToUpdatedEntity(UnitMeasureDto request, UnitMeasure entity) {
        entity.setUnitLabel(request.getUnitLabel());

        return entity;
    }
}
