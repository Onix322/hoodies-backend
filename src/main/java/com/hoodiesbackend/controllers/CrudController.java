package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.response.Response;

import java.util.List;
import java.util.Optional;

public interface CrudController<T> {

    Response<T> create(T body);

    Response<Optional<T>> get(Long id);

    Response<List<T>> getAll();

    Response<T> update(T entity);

    Response<T> delete(Long id);
}
