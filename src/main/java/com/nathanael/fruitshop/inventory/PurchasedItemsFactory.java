package com.nathanael.fruitshop.inventory;

import com.nathanael.fruitshop.global.ModelFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchasedItemsFactory implements ModelFactory<PurchasedItems, PurchasedItemsDto> {
    private final IngredientsServices ingredientsServices;
    private final PurchaseOrderServices purchaseOrderServices;
    private final IngredientsFactory ingredientsFactory;

    public PurchasedItemsFactory(@Lazy IngredientsServices ingredientsServices, @Lazy PurchaseOrderServices purchaseOrderServices, IngredientsFactory ingredientsFactory) {
        this.ingredientsServices = ingredientsServices;
        this.purchaseOrderServices = purchaseOrderServices;
        this.ingredientsFactory = ingredientsFactory;
    }

    @Override
    public PurchasedItems requestToEntity(PurchasedItemsDto request) {
        PurchasedItemsKey purchasedItemsKey = new PurchasedItemsKey(request.getPurchaseId(), request.getItemId());
        PurchasedItems purchasedItems = new PurchasedItems();
        purchasedItems.setPurchasedItemsKey(purchasedItemsKey);
        purchasedItems.setPurchaseOrder(purchaseOrderServices.getById(request.getPurchaseId()));
        purchasedItems.setIngredients(ingredientsServices.getById(request.getItemId()));
        purchasedItems.setQuantity(request.getQuantity());

        return purchasedItems;
    }

    @Override
    public PurchasedItemsDto entityToResponse(PurchasedItems entity, List<String> additionalFields) {
        PurchasedItemsDto purchasedItemsDto = new PurchasedItemsDto();
        purchasedItemsDto.setPurchaseId(entity.getPurchasedItemsKey().getPurchaseId());
        purchasedItemsDto.setItemId(entity.getPurchasedItemsKey().getItemId());
        purchasedItemsDto.setQuantity(entity.getQuantity());

        if (additionalFields != null) {
            if (additionalFields.remove("ingredient")) {
                purchasedItemsDto.setIngredientsDto(ingredientsFactory.entityToResponse(entity.getIngredients(), additionalFields));
                additionalFields.add("ingredient");
            }
        }

        return purchasedItemsDto;
    }
}
