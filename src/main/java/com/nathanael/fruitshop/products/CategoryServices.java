package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices implements EntityCrudServices<Category, CategoryDto, Long> {
    private final CategoryRepo categoryRepo;
    private final CategoryFactory categoryFactory;

    public CategoryServices(CategoryRepo categoryRepo, CategoryFactory categoryFactory) {
        this.categoryRepo = categoryRepo;
        this.categoryFactory = categoryFactory;
    }

    @Override
    public CategoryDto create(CategoryDto data) {
        return categoryFactory.entityToResponse(categoryRepo.save(categoryFactory.requestToEntity(data)), null);
    }

    @Override
    public CategoryDto update(CategoryDto data) {
        Category updatedCategory = categoryRepo.findById(data.getCategoryId())
                .map(category -> categoryRepo.save(categoryFactory.requestToUpdatedEntity(data, category)))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Category with an ID of " + data.getCategoryId() + " is not found.");
                });

        return categoryFactory.entityToResponse(updatedCategory, null);
    }

    @Override
    public CategoryDto delete(Long id) {
        CategoryDto deletedCategory = getDtoById(id, null);
        categoryRepo.deleteById(id);

        return deletedCategory;
    }

    @Override
    public CategoryDto getDtoById(Long id, List<String> additionalFields) {
        return categoryFactory.entityToResponse(getById(id), additionalFields);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Category with an ID of " + id + " is not found.");
        });
    }

    @Override
    public boolean entityExists(Long id) {
        return categoryRepo.findById(id).isPresent();
    }

    @Override
    public List<CategoryDto> getAll(List<String> additionalFields) {
        return categoryFactory.entityListToResponse(categoryRepo.findAll(), additionalFields);
    }
}
