package com.nathanael.fruitshop.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepo extends JpaRepository<Ingredients, Long> {
}
