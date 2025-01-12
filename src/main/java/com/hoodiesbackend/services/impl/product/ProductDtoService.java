package com.hoodiesbackend.services.impl.product;

import com.hoodiesbackend.entities.product.dtos.productDto.ProductDto;
import com.hoodiesbackend.entities.product.dtos.productDto.ProductMapper;
import com.hoodiesbackend.repositories.ProductRepository;
import com.hoodiesbackend.services.DtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDtoService implements DtoService<ProductDto> {
    private final ProductRepository productRepository;

    public ProductDtoService(ProductRepository productDtoRepository) {
        this.productRepository = productDtoRepository;
    }

    @Override
    public List<ProductDto> readAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto read() {
        return null;
    }
}
