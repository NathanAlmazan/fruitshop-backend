package com.nathanael.fruitshop.global;

import java.util.List;

public interface EntityCrudServices<T, U, X> {
    U create(U data);
    U update(U data);
    U delete(X id);
    U getDtoById(X id, List<String> additionalFields);
    T getById(X id);
    boolean entityExists(X id);
    List<U> getAll(List<String> additionalFields);
}
