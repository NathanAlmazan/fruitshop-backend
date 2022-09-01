package com.nathanael.fruitshop.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UnitMeasure {

    @Id
    @Column(length = 3, unique = true, nullable = false)
    private String unitCode;

    @Column(length = 15, nullable = false)
    private String unitLabel;

    @OneToMany(mappedBy = "unitType", fetch = FetchType.LAZY)
    private List<Product> unitProducts;
}
