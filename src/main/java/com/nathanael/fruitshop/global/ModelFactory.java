package com.nathanael.fruitshop.global;

import com.nathanael.fruitshop.products.Category;
import com.nathanael.fruitshop.products.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public interface ModelFactory<T, U> {
    T requestToEntity(U request);
    U entityToResponse(T entity, List<String> additionalFields);

    default List<U> entityListToResponse(List<T> entityList, List<String> additionalFields) {
        List<U> entityDtoList = new ArrayList<>(entityList.size());

        entityList.forEach(entity -> entityDtoList.add(entityToResponse(entity, additionalFields)));
        return entityDtoList;
    }
}
