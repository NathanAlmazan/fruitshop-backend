package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.products.Product;
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

    @Column
    private Integer quantity;
}
