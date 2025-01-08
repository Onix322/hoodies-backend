package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.services.impl.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product create(@RequestBody Product body) {
        System.out.println(body);
        return productService.create(body);
    }

    @GetMapping("/{id}")
    public Optional<Product> get(@PathVariable Long id) {
        return productService.read(id);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.readAll();
    }

    @PutMapping
    public Product update(@RequestBody Product entity) {
        return productService.update(entity);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
