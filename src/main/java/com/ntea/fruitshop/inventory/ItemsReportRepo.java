package com.ntea.fruitshop.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsReportRepo extends JpaRepository<ItemsReport, Long> {
    List<ItemsReport> findAllByOrderByTimestampDesc();
}
