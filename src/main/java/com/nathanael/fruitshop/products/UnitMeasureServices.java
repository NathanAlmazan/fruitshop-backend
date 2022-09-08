package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import com.nathanael.fruitshop.global.errors.InvalidRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitMeasureServices implements EntityCrudServices<UnitMeasure, UnitMeasureDto, String> {
    private final UnitMeasureRepo unitMeasureRepo;
    private final UnitMeasureFactory unitMeasureFactory;

    public UnitMeasureServices(UnitMeasureRepo unitMeasureRepo, UnitMeasureFactory unitMeasureFactory) {
        this.unitMeasureRepo = unitMeasureRepo;
        this.unitMeasureFactory = unitMeasureFactory;
    }

    @Override
    public UnitMeasureDto create(UnitMeasureDto data) {
        if (entityExists(data.getUnitCode()))
            throw new InvalidRequestException("Unit measure with code of " + data.getUnitCode() + " already exists.");

        return unitMeasureFactory.entityToResponse(
                unitMeasureRepo.save(unitMeasureFactory.requestToEntity(data)), null
        );
    }

    @Override
    public UnitMeasureDto update(UnitMeasureDto data) {
        UnitMeasure updatedUnits = unitMeasureRepo.findById(data.getUnitCode())
                .map(units -> unitMeasureRepo.save(unitMeasureFactory.requestToUpdatedEntity(data, units)))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Unit measure with code of " + data.getUnitCode() + " is not found.");
                });

        return unitMeasureFactory.entityToResponse(updatedUnits, null);
    }

    @Override
    public UnitMeasureDto delete(String id) {
        UnitMeasureDto deletedUnit = getDtoById(id, null);
        unitMeasureRepo.deleteById(id);

        return deletedUnit;
    }

    @Override
    public UnitMeasureDto getDtoById(String id, List<String> additionalFields) {
        return unitMeasureFactory.entityToResponse(getById(id), additionalFields);
    }

    @Override
    public UnitMeasure getById(String id) {
        return unitMeasureRepo.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Unit measure with code of " + id + " is not found.");
                });
    }

    @Override
    public boolean entityExists(String id) {
        return unitMeasureRepo.findById(id).isPresent();
    }

    @Override
    public List<UnitMeasureDto> getAll(List<String> additionalFields) {
        return unitMeasureFactory.entityListToResponse(unitMeasureRepo.findAll(), additionalFields);
    }
}
