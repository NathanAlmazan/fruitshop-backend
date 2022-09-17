package com.nathanael.fruitshop.inventory;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsServices implements EntityCrudServices<Ingredients, IngredientsDto, Long> {
    private final IngredientsFactory ingredientsFactory;
    private final IngredientsRepo ingredientsRepo;

    public IngredientsServices(IngredientsFactory ingredientsFactory, IngredientsRepo ingredientsRepo) {
        this.ingredientsFactory = ingredientsFactory;
        this.ingredientsRepo = ingredientsRepo;
    }

    @Override
    public IngredientsDto create(IngredientsDto data) {
        return ingredientsFactory.entityToResponse(
                ingredientsRepo.save(ingredientsFactory.requestToEntity(data)),
                null
        );
    }

    @Override
    public IngredientsDto update(IngredientsDto data) {
        Ingredients updated = ingredientsRepo.findById(data.getItemId())
                .map(item -> ingredientsRepo.save(ingredientsFactory.updateEntityFromRequest(data, item)))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Item with ID of " + data.getItemId() + " not found.");
                });

        return ingredientsFactory.entityToResponse(updated, null);
    }

    @Override
    public IngredientsDto delete(Long id) {
        IngredientsDto deleted = getDtoById(id, null);
        ingredientsRepo.deleteById(id);

        return deleted;
    }

    @Override
    public IngredientsDto getDtoById(Long id, List<String> additionalFields) {
        return ingredientsFactory.entityToResponse(getById(id), additionalFields);
    }

    @Override
    public Ingredients getById(Long id) {
        return ingredientsRepo.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Item with ID of " + id + " not found.");
                });
    }

    @Override
    public boolean entityExists(Long id) {
        return ingredientsRepo.findById(id).isPresent();
    }

    @Override
    public List<IngredientsDto> getAll(List<String> additionalFields) {
        return ingredientsFactory.entityListToResponse(ingredientsRepo.findAll(), additionalFields);
    }
}
