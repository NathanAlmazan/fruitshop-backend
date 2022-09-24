package com.nathanael.fruitshop.inventory;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServices implements EntityCrudServices<PurchaseOrder, PurchaseOrderDto, Long> {
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final PurchasedItemsRepo purchasedItemsRepo;
    private final PurchaseOrderFactory purchaseOrderFactory;
    private final PurchasedItemsFactory purchasedItemsFactory;
    private final IngredientsServices ingredientsServices;

    public PurchaseOrderServices(
            PurchaseOrderRepo purchaseOrderRepo,
            PurchasedItemsRepo purchasedItemsRepo,
            PurchaseOrderFactory purchaseOrderFactory,
            PurchasedItemsFactory purchasedItemsFactory,
            IngredientsServices ingredientsServices) {
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.purchasedItemsRepo = purchasedItemsRepo;
        this.purchaseOrderFactory = purchaseOrderFactory;
        this.purchasedItemsFactory = purchasedItemsFactory;
        this.ingredientsServices = ingredientsServices;
    }

    @Override
    public PurchaseOrderDto create(PurchaseOrderDto data) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.save(purchaseOrderFactory.requestToEntity(data));

        data.getPurchasedItems().forEach(item -> {
            item.setPurchaseId(purchaseOrder.getPurchaseId());
            purchasedItemsRepo.save(purchasedItemsFactory.requestToEntity(item));
            ingredientsServices.updateQuantity(item.getItemId(), item.getQuantity());
        });

        return purchaseOrderFactory.entityToResponse(purchaseOrder, null);
    }

    @Override
    public PurchaseOrderDto update(PurchaseOrderDto data) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(data.getPurchaseId())
                .map(purchase -> {
                    purchase.setPaid(data.getPaid());
                    purchase.setArrived(data.getArrived());

                    return purchaseOrderRepo.save(purchase);
                })
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Purchase with ID of " + data.getPurchaseId() + " is not found.");
                });

        return purchaseOrderFactory.entityToResponse(purchaseOrder, null);
    }

    @Override
    public PurchaseOrderDto delete(Long id) {
        PurchaseOrderDto purchaseOrderDto = getDtoById(id, null);
        purchaseOrderRepo.deleteById(id);

        return purchaseOrderDto;
    }

    @Override
    public PurchaseOrderDto getDtoById(Long id, List<String> additionalFields) {
        return purchaseOrderFactory.entityToResponse(getById(id), additionalFields);
    }

    @Override
    public PurchaseOrder getById(Long id) {
        return purchaseOrderRepo.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Purchase with ID of " + id + " is not found.");
        });
    }

    @Override
    public boolean entityExists(Long id) {
        return false;
    }

    @Override
    public List<PurchaseOrderDto> getAll(List<String> additionalFields) {
        return purchaseOrderFactory.entityListToResponse(purchaseOrderRepo.findAll(), additionalFields);
    }

}
