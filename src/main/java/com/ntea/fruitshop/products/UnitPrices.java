package com.ntea.fruitshop.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UnitPrices {

    @EmbeddedId
    private UnitPriceKey unitPriceKey;

    @ManyToOne
    @MapsId("productCode")
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne
    @MapsId("unitCode")
    @JoinColumn(name = "unit_code")
    private UnitMeasure unitType;

    @Column(nullable = false)
    private Double unitPrice;
}
