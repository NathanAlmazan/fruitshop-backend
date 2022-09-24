package com.nathanael.fruitshop.inventory;

import com.nathanael.fruitshop.global.ModelFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseOrderFactory implements ModelFactory<PurchaseOrder, PurchaseOrderDto> {
    private final PurchasedItemsFactory purchasedItemsFactory;

    public PurchaseOrderFactory(PurchasedItemsFactory purchasedItemsFactory) {
        this.purchasedItemsFactory = purchasedItemsFactory;
    }

    @Override
    public PurchaseOrder requestToEntity(PurchaseOrderDto request) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setSupplier(request.getSupplier());
        purchaseOrder.setTotalPrice(request.getTotalPrice());
        purchaseOrder.setPaid(request.getPaid());
        purchaseOrder.setArrived(request.getArrived());
        purchaseOrder.setReceiptURL(request.getReceiptURL());
        purchaseOrder.setDueDate(request.getDueDate());

        return purchaseOrder;
    }

    @Override
    public PurchaseOrderDto entityToResponse(PurchaseOrder entity, List<String> additionalFields) {
        PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
        purchaseOrderDto.setPurchaseId(entity.getPurchaseId());
        purchaseOrderDto.setSupplier(entity.getSupplier());
        purchaseOrderDto.setTotalPrice(entity.getTotalPrice());
        purchaseOrderDto.setPaid(entity.getPaid());
        purchaseOrderDto.setArrived(entity.getArrived());
        purchaseOrderDto.setPurchaseDate(entity.getPurchaseDate());
        purchaseOrderDto.setReceiptURL(entity.getReceiptURL());
        purchaseOrderDto.setDueDate(entity.getDueDate());

        if (additionalFields != null) {
            if (additionalFields.remove("items")) {
                purchaseOrderDto.setPurchasedItems(purchasedItemsFactory.entityListToResponse(entity.getPurchasedItems(), additionalFields));
                additionalFields.add("items");
            }
        }

        return purchaseOrderDto;
    }
}
