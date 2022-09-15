package com.nathanael.fruitshop.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class OrderItemKey implements Serializable {

    @Column(nullable = false)
    private Long orderId;

    @Column(length = 3, nullable = false)
    private String productCode;

    @Column(length = 3, nullable = false)
    private String typeCode;
}
