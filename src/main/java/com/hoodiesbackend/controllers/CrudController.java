package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;

import java.util.List;
import java.util.Optional;

public interface CrudController<T> {

    void create(T body);

    Optional<T> get(Long id);

    List<T> getAll();

    void update(T entity);

    Boolean delete(Long id);
}
