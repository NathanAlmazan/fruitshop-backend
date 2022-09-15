package com.nathanael.fruitshop.products;

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
public class UnitPriceKey implements Serializable {
    @Column private String unitCode;
    @Column private String productCode;
}
