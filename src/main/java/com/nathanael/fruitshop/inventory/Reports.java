package com.nathanael.fruitshop.inventory;

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
public class Reports {

    @Id
    @SequenceGenerator(name = "reports_report_id", sequenceName = "reports_report_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reports_report_id")
    @Column(updatable = false)
    private Long reportId;

    @Column(length = 20, nullable = false)
    private String reporter;

    @Column(nullable = false)
    private Double projectedCost;

    @Column
    private LocalDateTime reportDate = LocalDateTime.now();

    @OneToMany(mappedBy = "reports", fetch = FetchType.LAZY)
    private List<ReportedItems> reportedItems;

}
