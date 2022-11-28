package com.ntea.fruitshop.sales;

import com.ntea.fruitshop.products.Product;
import com.ntea.fruitshop.products.UnitMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItems {
    @EmbeddedId
    private OrderItemKey orderItemKey;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @MapsId("productCode")
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne
    @MapsId("typeCode")
    @JoinColumn(name = "type_code")
    private UnitMeasure unitType;

    @Column
    private Integer quantity;
}
