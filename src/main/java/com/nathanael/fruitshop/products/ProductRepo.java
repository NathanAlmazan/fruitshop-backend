package com.nathanael.fruitshop.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductRepo extends JpaRepository<Product, String> {

    @Modifying
    @Query("UPDATE Product p SET p.isActive = false WHERE p.productCode = ?1")
    void setProductInactive(String code);

    @Query("SELECT p FROM Product p WHERE p.isActive = true")
    List<Product> findAllActiveProducts();

    @Query("SELECT p FROM Product p WHERE p.isActive = false")
    List<Product> findAllInActiveProducts();
}
