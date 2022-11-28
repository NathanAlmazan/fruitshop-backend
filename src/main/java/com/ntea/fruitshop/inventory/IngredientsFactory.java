package com.ntea.fruitshop.inventory;

import com.ntea.fruitshop.global.ModelFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class IngredientsFactory implements ModelFactory<Ingredients, IngredientsDto> {

    @Override
    public Ingredients requestToEntity(IngredientsDto request) {
        Ingredients ingredients = new Ingredients();
        ingredients.setItemName(request.getItemName());
        ingredients.setBrandName(request.getBrandName());
        ingredients.setUnits(request.getUnits());
        ingredients.setInStock(request.getInStock());
        ingredients.setUnitPrice(request.getUnitPrice());

        return ingredients;
    }

    @Override
    public IngredientsDto entityToResponse(Ingredients entity, List<String> additionalFields) {
        IngredientsDto ingredientsDto = new IngredientsDto();
        ingredientsDto.setItemId(entity.getItemId());
        ingredientsDto.setItemName(entity.getItemName());
        ingredientsDto.setBrandName(entity.getBrandName());
        ingredientsDto.setUnits(entity.getUnits());
        ingredientsDto.setInStock(entity.getInStock());
        ingredientsDto.setUnitPrice(entity.getUnitPrice());
        ingredientsDto.setLastUpdated(entity.getTimestamp());

        return ingredientsDto;
    }

    public Ingredients updateEntityFromRequest(IngredientsDto request, Ingredients entity) {
        entity.setItemName(request.getItemName());
        entity.setBrandName(request.getBrandName());
        entity.setUnits(request.getUnits());
        entity.setInStock(request.getInStock());
        entity.setUnitPrice(request.getUnitPrice());
        entity.setTimestamp(LocalDateTime.now());

        return entity;
    }
}
