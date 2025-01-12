package com.hoodiesbackend.services;

import com.hoodiesbackend.entities.product.dtos.productDto.ProductDto;

import java.util.List;

public interface DtoService<T> {

    List<T> readAll();

    T read();
}
