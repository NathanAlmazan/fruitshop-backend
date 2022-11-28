package com.ntea.fruitshop.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemsReport implements Serializable {

    @Id
    @SequenceGenerator(name = "items_report_id", sequenceName = "items_report_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_report_id")
    @Column(updatable = false)
    private Long reportId;

    @Column(nullable = false)
    private Integer reportedQuantity;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "itemId", nullable = false)
    private Ingredients item;

    @Column
    private LocalDateTime timestamp = LocalDateTime.now();
}
