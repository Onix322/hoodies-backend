package com.hoodiesbackend.services.impl;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.ProductRepository;
import com.hoodiesbackend.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements CrudService<Product> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        System.out.println(product);
        return productRepository.save(product);
    }

    @Override
    public Product read(Long id) {
        if(id<=0) {
            throw new BadRequestException("Id is invalid!");
        }
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Resource not found"));
    }

    @Override
    public List<Product> readAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product delete(Long id) {
        Product product = this.read(id);
        if(id<=0) {
            throw new BadRequestException("Id is invalid!");
        }
        productRepository.deleteById(product.getId());
        return product;
    }
}
