package com.nathanael.fruitshop.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @Column(length = 3, unique = true, nullable = false)
    private String productCode;

    @Column(length = 30, nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_type", referencedColumnName = "unitCode", nullable = false)
    private UnitMeasure unitType;

    @Column(nullable = false)
    private Double unitPrice;

    @Column
    private Double discountedPrice = 0.00;

    @Column
    private String productImage;

    @Column
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category", referencedColumnName = "categoryId", nullable = false)
    private Category productCategory;
}
