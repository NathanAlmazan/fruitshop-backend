package com.ntea.fruitshop.sales;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @SequenceGenerator(name = "order_order_id_seq", sequenceName = "order_order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_order_id_seq")
    @Column(updatable = false)
    private Long orderId;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(length = 5, nullable = false)
    private String paymentType;

    @Column(length = 13)
    private String transactionId;

    @Column
    private Double paidAmount;

    @Column
    private Double discount;

    @Column
    private Boolean cancelled = false;

    @Column
    private LocalDateTime timestamp = LocalDateTime.now();

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrderItems> orderItems;
}
