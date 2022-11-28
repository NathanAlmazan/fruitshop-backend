package com.ntea.fruitshop.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @SequenceGenerator(name = "categories_category_id_seq", sequenceName = "categories_category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_category_id_seq")
    @Column(updatable = false)
    private Long categoryId;

    @Column(length = 15, nullable = false)
    private String categoryName;

    @Column
    private String categoryIcon;

    @Column(nullable = false)
    private Boolean rawMaterial = false;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    List<Product> categoryProducts;
}
