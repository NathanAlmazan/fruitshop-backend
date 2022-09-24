package com.nathanael.fruitshop.inventory;

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
public class PurchaseOrder {

    @Id
    @SequenceGenerator(name = "purchase_order_id", sequenceName = "purchase_order_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_id")
    @Column(updatable = false)
    private Long purchaseId;

    @Column(length = 30, nullable = false)
    private String supplier;

    @Column(nullable = false)
    private Double totalPrice;

    @Column
    private Boolean paid = false;

    @Column
    private Boolean arrived = false;

    @Column(nullable = false)
    private String receiptURL;

    @Column
    private LocalDateTime dueDate;

    @Column
    private LocalDateTime purchaseDate = LocalDateTime.now();

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY)
    private List<PurchasedItems> purchasedItems;
}
