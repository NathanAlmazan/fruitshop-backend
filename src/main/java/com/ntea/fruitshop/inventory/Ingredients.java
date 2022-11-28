package com.ntea.fruitshop.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ingredients {

    @Id
    @SequenceGenerator(name = "ingredients_item_id", sequenceName = "ingredients_item_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredients_item_id")
    @Column(updatable = false)
    private Long itemId;

    @Column(length = 30, nullable = false)
    private String itemName;

    @Column(length = 20)
    private String brandName;

    @Column(length = 3, nullable = false)
    private String units;

    @Column(nullable = false)
    private Integer inStock;

    @Column(nullable = false)
    private Double unitPrice;

    @Column
    private LocalDateTime timestamp = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private List<PurchasedItems> purchasedItems;

    @JsonIgnore
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ItemsReport> itemsReports;
}
