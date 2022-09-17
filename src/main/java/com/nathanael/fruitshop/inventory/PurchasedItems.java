package com.nathanael.fruitshop.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PurchasedItems {

    @EmbeddedId
    private PurchasedItemsKey purchasedItemsKey;

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Ingredients ingredients;

    @Column(nullable = false)
    private Integer quantity;

}
