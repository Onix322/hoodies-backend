package com.hoodiesbackend.services;

import com.hoodiesbackend.entities.product.Product;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    T create(T entity);

    Optional<T> read(Long id);

    List<T> readAll();

    Product update(T entity);

    Boolean delete(Long id);
}
