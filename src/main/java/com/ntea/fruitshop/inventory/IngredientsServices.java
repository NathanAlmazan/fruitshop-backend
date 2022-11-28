package com.ntea.fruitshop.inventory;

import com.ntea.fruitshop.global.EntityCrudServices;
import com.ntea.fruitshop.global.errors.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientsServices implements EntityCrudServices<Ingredients, IngredientsDto, Long> {
    private final IngredientsFactory ingredientsFactory;
    private final IngredientsRepo ingredientsRepo;
    private final ItemsReportRepo itemsReportRepo;

    public IngredientsServices(IngredientsFactory ingredientsFactory, IngredientsRepo ingredientsRepo, ItemsReportRepo itemsReportRepo) {
        this.ingredientsFactory = ingredientsFactory;
        this.ingredientsRepo = ingredientsRepo;
        this.itemsReportRepo = itemsReportRepo;
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
                .map(item -> {
                    if (!Objects.equals(item.getInStock(), data.getInStock())) {
                        ItemsReport itemsReport = new ItemsReport();
                        itemsReport.setReportedQuantity(data.getInStock());
                        itemsReport.setItem(getById(item.getItemId()));

                        itemsReportRepo.save(itemsReport);
                    }

                    return ingredientsRepo.save(ingredientsFactory.updateEntityFromRequest(data, item));
                })
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

    public void updateQuantity(Long id, Integer quantity) {
        ingredientsRepo.findById(id)
            .map(item -> {
                Integer updatedStock = item.getInStock() + quantity;
                ItemsReport itemsReport = new ItemsReport();
                itemsReport.setReportedQuantity(updatedStock);
                itemsReport.setItem(getById(item.getItemId()));
                itemsReportRepo.save(itemsReport);

                item.setInStock(updatedStock);
                return ingredientsRepo.save(item);
            })
            .orElseThrow(() -> {
                throw new EntityNotFoundException("Item with ID of " + id + " not found.");
            });
    }

    public List<ItemsReport> getItemsReport() {
        return itemsReportRepo.findAllByOrderByTimestampDesc();
    }
}
