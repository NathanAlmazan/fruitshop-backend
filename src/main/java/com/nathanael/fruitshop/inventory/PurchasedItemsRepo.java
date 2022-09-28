package com.nathanael.fruitshop.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchasedItemsRepo extends JpaRepository<PurchasedItems, PurchasedItemsKey> {

    @Query("SELECT t.purchasedItemsKey.itemId AS itemId, SUM(t.quantity) AS itemCount FROM PurchasedItems AS t GROUP BY t.purchasedItemsKey.itemId")
    List<MostPurchasedItems> getMostPurchasedItems();

}
