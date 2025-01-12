package com.hoodiesbackend.services;

import com.hoodiesbackend.entities.product.Product;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    T create(T entity);

    T read(Long id);

    Product update(Product product);

    Product delete(Long id);
}
