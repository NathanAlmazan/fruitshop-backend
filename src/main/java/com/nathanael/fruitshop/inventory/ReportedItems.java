package com.nathanael.fruitshop.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReportedItems {

    @EmbeddedId
    private ReportedItemsKey reportedItemsKey;

    @ManyToOne
    @MapsId("reportId")
    @JoinColumn(name = "report_id")
    private Reports reports;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Ingredients ingredients;

    @Column(nullable = false)
    private Integer inStock;

}
