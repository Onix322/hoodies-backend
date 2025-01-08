package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudController<T> {

    ResponseEntity<Response> create(T body);

    ResponseEntity<Response> get(Long id);

    ResponseEntity<Response> getAll();

    ResponseEntity<Response> update(T entity);

    ResponseEntity<Response> delete(Long id);
}
