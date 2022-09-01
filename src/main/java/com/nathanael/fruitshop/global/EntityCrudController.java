package com.nathanael.fruitshop.global;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EntityCrudController<T, X> {
    ResponseEntity<T> create(T data);
    ResponseEntity<T> update(T data, X id);
    ResponseEntity<T> delete(X id);
    ResponseEntity<T> getById(X id, List<String> additionalFields);
    ResponseEntity<List<T>> getAll(List<String> additionalFields);
}
